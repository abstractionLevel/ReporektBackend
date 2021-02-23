import React, { useState, useRef, useEffect, useReducer } from "react";
import { useForm } from "react-hook-form";
import { Col, Container, Row } from 'react-bootstrap';
//service
import AuthService from '../../service/auth.service';

//scss
import './RegisterPage.scss';


const RegisterPage = (props) => {


    const { register, errors, handleSubmit, watch } = useForm({});

    const passwordWatched = useRef({});
    const submitRef = useRef();


    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [usernameAlreadyExist, setUsernameAlreadyExist] = useState();
    const [emailAlreadyExists, setEmailAlreadyExists] = useState();

    passwordWatched.current = watch("password", "")

    useEffect(() => {
    }, [usernameAlreadyExist])



    const onSubmit = data => {
        if(submitRef.current) {
            submitRef.current.setAttribute("disabled", "disabled");
        }
        AuthService.verifyUserExists(data.username).then(
            (response) => {
                response.status === 200 ? setUsernameAlreadyExist(true) : setUsernameAlreadyExist(false)
            }
        );

        AuthService.verifyEmailExists(data.email).then(
            (response) => {
                response.status === 200 ? setEmailAlreadyExists(true) : setEmailAlreadyExists(false)
            }
        );

        setUsername(data.username)
        setPassword(data.password)
        setEmail(data.email)
        setConfirmPassword(data.confirmPassword)

    };
    if (usernameAlreadyExist === false && emailAlreadyExists === false) {
        AuthService.register(username, email, password, confirmPassword).then(
            (response) => {
                props.history.push({
                    pathname: 'message',
                    state: {
                        successful: true,
                        message: "A verification link has been sent your email: " + email
                    }
                });
            },
            (error) => {
                console.log(error)
                const resMessage =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();

                props.history.push({
                    pathname: 'message',
                    state: {
                        successful: false,
                        message: resMessage
                    }
                });
            }
        );
    }



    return (
        <Container fluid>
            <Row className="justify-content-md-center">
                <Col className="col-lg-4 form-login">
                    <h5>Sign Up</h5>
                    <form onSubmit={handleSubmit(onSubmit)}>
                        <input
                            name="username"
                            className="form-control mb-2 "
                            type="text"
                            placeholder="username"
                            ref={register({
                                required: "You must specify a username",
                                minLength: {
                                    value: 8,
                                    message: "username must have at least 6 characters"
                                },
                                 pattern: {
                                    value:  /^[a-zA-Z0-9_]+$/,
                                    message: "No Speacial Characters"
                                },
                                
                            })}
                        />
                        {errors.username && <p>{errors.username.message}</p>}
                        {usernameAlreadyExist && <p>Username Already Exists</p>}

                        <input
                            name="email"
                            className="form-control mb-2 "
                            type="text"
                            placeholder="email"
                            ref={register({
                                required: "required",
                                pattern: {
                                    value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                                    message: "invalid email address"
                                }
                            })}
                        />

                        {errors.email && <p>{errors.email.message}</p>}
                        {emailAlreadyExists && <p>email Already exists</p>}

                        <input
                            name="password"
                            className="form-control mb-2 "
                            type="password"
                            placeholder="password"
                            ref={register({
                                required: "You must specify a password",
                                minLength: {
                                    value: 8,
                                    message: "Password must have at least 8 characters"
                                }
                            })}
                        />
                        {errors.password && <p>{errors.password.message}</p>}

                        <input
                            name="confirmPassword"
                            className="form-control mb-2"
                            placeholder="confirm password"
                            type="password"
                            ref={register({
                                validate: value =>
                                    value === passwordWatched.current || "The passwords do not match"
                            })}
                        />
                        {errors.confirmPassword && <p>{errors.confirmPassword.message}</p>}

                        <input type="submit" className="form-control " ref={submitRef}/>
                    </form>
                </Col>
            </Row>
        </Container>
    )

};

export default RegisterPage;
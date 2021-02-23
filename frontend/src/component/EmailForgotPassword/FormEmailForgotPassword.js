import React, { useState, useEffect, useRef } from 'react';
import { useForm } from 'react-hook-form';
import './FormEmailForgotPassword.scss';
import { useHistory } from 'react-router';
import AuthService from '../../service/auth.service';



const FormEmailForgotPassword = (props) => {

    let history = useHistory()
    let btnRef = useRef();
    const { register, errors, handleSubmit } = useForm({});
    const [successful, setSuccessful] = useState(false)
    const [submit, setSubmit] = useState(false)
    const [response, setResponse] = useState();


    const onSubmit = async (data) => {
        if (btnRef.current) {
            btnRef.current.setAttribute("disabled", "disabled");
        }
        await AuthService.sendEmailForgotPassword(data.email).then(
            (response) => {
                response === 200 ? setSuccessful(true) : setSuccessful(false)
                setResponse(response)
            });
        setSubmit(true)
    };

    useEffect(() => {
        if (response === 200) {
            setTimeout(() => {
                btnRef.current.setAttribute("enabled", "enabled");
                history.push('/')
            }, 1000)
        }
        else {
            btnRef.current.setAttribute("enabled", "enabled");
        }
    }, [response])

    return (
        <div>
            {submit && (successful ? (<div className="alert alert-success">A verification link has been sent your email</div>)
                : (<div className="alert alert-danger">there is some problem with the request</div>)
            )}
            <form onSubmit={handleSubmit(onSubmit)}>
                <input
                    type="text"
                    placeholder="email"
                    name="email"
                    className="form-control mb-2"
                    ref={register({
                        required: "required"
                    })}
                />
                <input
                    type="submit"
                    className="form-control"
                    ref={btnRef}
                />
                {errors.email && <p>{errors.email.message}</p>}
            </form>
        </div>


    );

};

export default FormEmailForgotPassword;
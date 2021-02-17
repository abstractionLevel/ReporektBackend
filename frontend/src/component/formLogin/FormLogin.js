import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import AuthService from '../../service/auth.service';
import './FormLogin.scss';
import { useHistory } from "react-router"





const FormLogin = (props) => {

    let history = useHistory()
    const { register, errors, handleSubmit } = useForm({});
    const [successful, setSuccessful] = useState(true);

    const onSubmit = data => {
        AuthService.login(data.username, data.password).then(
            (response) => {
                history.push('/');
                window.location.reload();

            },
            (error) => {
                setSuccessful(false)
            }
        );
    };

    return (
        <div>
            <form onSubmit={handleSubmit(onSubmit)}>
                <input
                    type="text"
                    className="form-control "
                    name="username"
                    placeholder="username"
                    ref={register({
                        required: "required"
                    })}
                />
                {errors.username && <p>{errors.username.message}</p>}
                <input
                    type="text"
                    placeholder="password"
                    className="form-control mt-4 mb-4"
                    name="password"
                    ref={register({
                        required: "requred"
                    })}
                />
                {errors.password && <p>{errors.password.message}</p>}
                <input
                    type="submit"
                    className="form-control"
                />
            </form>
            { !successful && <p>username or password incorrect</p>}
        </div>
    );

};

export default FormLogin;
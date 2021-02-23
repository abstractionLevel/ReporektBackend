//component
import Header from './header/Header';
import HomePage from './pages/homePage/HomePage';
import PlayerPage from './pages/playerPage/PlayerPage';
import LoginPage from './pages/Login/LoginPage';
import RegisterPage from './pages/Register/RegisterPage';
import Message from './pages/Message/Message';
import ReporterPage from './pages/Report/ReporterPage';
import PrivateRoute from './utility/PrivateRoute';
import LoggedRouter from './utility/LoggedRouter';
import React, { useState, useEffect, useReducer } from "react";
import { Route, Switch, BrowserRouter as Router } from 'react-router-dom';
import PasswordForgotPage from './pages/PasswordForgotPage/PasswordForgotPage';
import EmailForgotPasswordPage from './pages/EmailForgotPasswordPage/EmailForgotPasswordPage';
import CookieConsent from "react-cookie-consent";
import Cookies from 'js-cookie';


//class
import './assets/scss/default.scss';
//service
import AuthService from './service/auth.service';
import UserProfile from './pages/userPage/UserProfile';



const App = (props) => {

    const message = "We & our technology partners ask you to consent to the use of cookies to store and access personal data on your device. By clicking below you are consenting to the use of this technology.";

    const userRegion = localStorage.getItem("userRegion");
    let acconsentCookie = Cookies.get("acconsent");

    const [currentUser, setCurrentUser] = useState(undefined);



    useEffect(() => {
        const user = AuthService.getCurrentUser();
        if (user) {
            setCurrentUser(user);
        }

    }, [userRegion]);


    return (
        <div>
            <Header user={currentUser} />
            <CookieConsent
                location="top"
                buttonText="Accept"
                cookieName="acconsent"
                style={{ background: "#2B373B" }}
                buttonStyle={{ color: "#4e503b", fontSize: "13px" }}
                expires={150}
                onAccept={() => {
                    window.location.reload();
                  }}
            >
                {message}
            </CookieConsent>
            {userRegion ? (
                <div className="App">
                    <div className="image">
                        <Switch>
                            <Route exact path='/' component={HomePage} />
                            <Route exact path="/register" component={RegisterPage} />
                            <PrivateRoute exact path='/report' component={ReporterPage} />
                            <LoggedRouter exact path="/login" component={LoginPage} />
                            <Route exact path='/emailForgotPassword' component={EmailForgotPasswordPage} />
                            <Route exact path="/message" component={Message} />
                            <Route exact path='/player/:username' component={PlayerPage} />
                            <Route exact path='/forgotPassword/:token' component={PasswordForgotPage} />
                            <Route exact path='/profile' component={UserProfile} />
                        </Switch>
                    </div>
                </div>) : (<p></p>)
            }
        </div>
    )

}

export default App;

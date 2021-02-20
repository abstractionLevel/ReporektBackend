//component
import Header from './header/Header';
import HomePage from './pages/homePage/HomePage';
import PlayerPage from './pages/playerPage/PlayerPage';
import LoginPage from './pages/Login/LoginPage';
import RegisterPage from './pages/Register/RegisterPage';
import HomeProva from './pages/homePage/HomeProva';
import Message from './pages/Message/Message';
import ReporterPage from './pages/Report/ReporterPage';
import PrivateRoute from './utility/PrivateRoute';
import LoggedRouter from './utility/LoggedRouter';
import React, { useState, useEffect, useReducer } from "react";
import { Route, Switch, BrowserRouter as Router } from 'react-router-dom';
import PasswordForgotPage from './pages/PasswordForgotPage/PasswordForgotPage';
import EmailForgotPasswordPage from './pages/EmailForgotPasswordPage/EmailForgotPasswordPage';
import { CookieBanner } from '@palmabit/react-cookie-law';

//class
import './assets/scss/default.scss';
//service
import AuthService from './service/auth.service';
import UserProfile from './pages/userPage/UserProfile';



const App = (props) => {

    const message = "Noi e i nostri partner utilizziamo, sul nostro sito, tecnologie come i cookie per personalizzare contenuti e annunci, fornire funzionalità per social media e analizzare il nostro traffico. Facendo clic di seguito si acconsente all'utilizzo di questa tecnologia. Puoi cambiare idea e modificare le tue scelte sul consenso in qualsiasi momento ritornando su questo sito.";

    const userRegion = localStorage.getItem("userRegion");

    const [currentUser, setCurrentUser] = useState(undefined);


    useEffect(() => {
        const user = AuthService.getCurrentUser();
        if (user) {
            setCurrentUser(user);
        }
      

    }, [userRegion]);

    const onDecline = () => {
    }

    const onAccept = () => {
    }

    return (
        <div>
            <Header user={currentUser} />
            {userRegion ? (
                <div className="App">
                    <CookieBanner
                       message={message}
                       policyLink="/#"
                       privacyPolicyLinkText="Privacy Policy"
                       acceptButtonText="Accept"
                       showDeclineButton={false}
                       dismissOnScroll={false}
                       showStatisticsOption={false}
                       showMarketingOption={false}
                       showPreferencesOption={false}
                       onAcceptPreferences={() => console.log('onAcceptPreferences')}
                       onAcceptStatistics={() => false}
                       onAcceptMarketing={() => false}
                       onDeclinePreferences={() => false}
                       onDeclineStatistics={() =>false}
                       onDeclineMarketing={() => false}
                    />
                    <div className="image">
                        <Switch>
                            <Route exact path='/'  component={HomePage} />
                            <Route exact path="/register" component={RegisterPage} />
                            <PrivateRoute exact path='/report' component={ReporterPage} />
                            <LoggedRouter  exact path="/login" component={LoginPage} />
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

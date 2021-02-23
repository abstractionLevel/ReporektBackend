import React, { useState, useEffect } from 'react';
import { useParams } from "react-router-dom";
import { Col, Container, Row } from 'react-bootstrap';

//Component
import CardReportsOfPlayer from '../../report/CardReportsOfPlayer';
import PieChartReportsTypeOfPlayer from '../../component/PieChartReportsTypeOfPlayer/PieChartReportsTypeOfPlayer';
import PlayerService from '../../service/player.service';
//css
import './playerPage.scss';
//assets

const PlayerPage = () => {

    const { username } = useParams()
    const [checkPlayer, setChekPlayer] = useState(false)
    const [message,setMessage] = useState("");


    
    useEffect(()=>{
        if(!checkPlayer) setMessage("Not Found")
    },[])

    PlayerService.searchPlayer(username).then(
        (response) => {
            response ? setChekPlayer(true) : setChekPlayer(false)
        }
    );
    return (
        <div>
            {checkPlayer ? (
                <Container fluid  >
                    <Row>
                        <Col className='col-card-report col-lg-6 '>
                            <div>
                                <p className="username">{username}</p>
                            </div>
                            <div>
                                <CardReportsOfPlayer username={username} />
                            </div>
                        </Col>
                        <Col className='report-type-players mb-5  ml-5 col-lg-4'>
                            <div>
                                <h5 ></h5>
                                <PieChartReportsTypeOfPlayer username={username} />
                            </div>

                        </Col>
                    </Row>
                </Container> ): (
                <Container fluid  >
                    <Row className="">
                        <Col className=''>
                            <h2 className="d-flex justify-content-center mt-5">{message}</h2>
                        </Col>
                    </Row>
                </Container>
           )}
        </div>

    );
}

export default PlayerPage;
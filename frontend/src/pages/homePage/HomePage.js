import React from 'react';
import { Col, Container, Row } from 'react-bootstrap';

//component
import ListLatestPlayers from '../../reportType/ListLatestPlayers'
import CardTopPlayers from '../../players/component/CardTopPlyers';
import PieChartReportsTypeOfRegion from '../../component/PieChartReportsTypeOfRegion/PieChartReportsTypeOfRegion';
import CardLastReport from '../../component/CardLastReport/CardLastReport';
//css
import './homepage.scss'

const HomePage = () => {
    return (
        <Container fluid className='mt-5'>
            <Row>
                <Col className='col list-top-players mr-2 '>
                    <div className="mt-2">
                    
                        <p className="top-player d-none d-sm-block d-md-none d-block d-sm-none">top player</p>
                        <CardTopPlayers />
                    </div>
                </Col>
                <Col className='col-md-4 card-last-report '>
                    <div className="mt-2">
                        <p className="last-player d-none d-sm-block d-md-none d-block d-sm-none">top player</p>
                        <CardLastReport />
                    </div>
                </Col>
                <Col className='col-md-5 list-reportType'>
                    <h5>All Report Type of Region</h5>
                    <PieChartReportsTypeOfRegion />
                </Col>
            </Row>
        </Container>
    );

}
export default HomePage;

import React, { useEffect, useState } from 'react';
import { Card } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import ReportService from '../../service/report.service';
import './CardLastReport.scss';

//time
import Moment from 'react-moment';
import 'moment-timezone';


const CardLastReport = () => {

    const [reports,setReports] = useState([]);

    useEffect(()=>{
        ReportService.fetchLastReportOfRegion().then(
            (response)=>{
                setReports(response)
            }
        );
    },[]);

    return (
            reports.map((report, index) =>
                <div key={index} className="card-top-players">
                    <Card >
                        <Card.Body>
                            <Card.Title className="">
                                <div className="mb-1"><Link to={"player/"+report.player}>{report.player}</Link></div>
                                {report.description}
                            </Card.Title>
                            <div className="d-flex ">
                                <div className="mr-auto ">Date : <Moment format="YYYY/MM/DD">{report.date}</Moment></div>
                                <div className='d-inline report-type ml-2 mr-2'>{report.reportType}</div>
                            </div>
                        </Card.Body>
                    </Card>
                    <br />
                </div>
            )
    );

};
export default CardLastReport;
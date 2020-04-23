import * as React from "react";
import './progress-loader.css';
import {Spinner} from "react-bootstrap";

export default class ProgressLoader extends React.Component {
    render() {
        return <div className="loader">
            <Spinner animation="border" role="status"/>
        </div>;
    }
}
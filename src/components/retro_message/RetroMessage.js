import * as React from "react";
import './retro-message.css';
import {Card} from "react-bootstrap";


export default class RetroMessage extends React.Component {
    render() {
        return <Card bg="light" text="black" className="m-3">
            <Card.Header className={this.props.type}>
                <div className="d-flex light justify-content-between retro-message-header-text">
                    <span>{this.props.author}</span>
                    <span>{this.props.date}</span>
                </div>
            </Card.Header>

            <Card.Body>
                <Card.Title className="retro-message-text">{this.props.text}</Card.Title>
            </Card.Body>
        </Card>
    }
}
import * as React from "react";
import './empty-container.css';

export default class EmptyContainer extends React.Component {
    render() {
        return <div className="empty-container">
            <h1>NO MESSAGES</h1>
        </div>;
    }
}
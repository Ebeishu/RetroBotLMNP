import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from './components/header/Header'
import RetroMessageContainer from "./components/retro_message_container/RetroMessageContainer";

export default class App extends React.Component {

    render() {
        return <div className="App">
            <Header onDateChange = {(startDate, endDate) => this.whenDateChange(startDate, endDate)}/>
            <RetroMessageContainer ref="child"/>
        </div>
    }

    whenDateChange(startDate, endDate) {
        this.refs.child.refreshMessages(startDate, endDate);
    }
}

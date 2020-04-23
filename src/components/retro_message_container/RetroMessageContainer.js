import * as React from "react";
import RetroMessage from "../retro_message/RetroMessage";
import ProgressLoader from "../progress_loader/ProgressLoader";
import MessageApi from "../../api/MessageApi";
import EmptyContainer from "../empty_container/EmptyContainer";

export default class RetroMessageContainer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {data: {}, isFetching: true};
        this.messageApi = new MessageApi();
    }

    componentDidMount() {
        this.refreshMessages();
    };

    render() {
        if (this.state.isFetching) return <ProgressLoader/>;
        if (this.state.data.length === 0) return <EmptyContainer/>;
        return this.state.data.map(message => this.toMessage(message));
    }


    toMessage(message) {
        return <RetroMessage
            text={message.text}
            date={message.createdAt.substr(0, 16).replace("T", " ")}
            author={message.sender.firstName + " " + message.sender.lastName}/>
    }


    refreshMessages(startDate, endDate) {
        this.setState({isFetching: true});
        this.messageApi.fetchMessages(result => this.setState({data: result, isFetching: false}), startDate, endDate);
    }
}
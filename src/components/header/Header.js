import * as React from "react";
import './header.css';
import {Navbar} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faClock} from "@fortawesome/free-solid-svg-icons";
import DatePicker from "../date_picker/DatePicker";

class Header extends React.Component {

    constructor(props) {
        super(props);
        this.state = {datePickerShown: false};
        this.toggleDatePicker = this.toggleDatePicker.bind(this);
        this.onDatesSelected = this.onDatesSelected.bind(this);
    }

    render = () => (
        <Navbar sticky="top" bg="info">
            <Navbar.Brand href="#home" className='header-brand ml-2'>#RETROBOT</Navbar.Brand>
            <Navbar.Collapse className="justify-content-end">
                <div className="date-picker-toggler">
                    <FontAwesomeIcon size="2x" color="dark" icon={faClock} className='mr-2'
                                     onClick={this.toggleDatePicker}/>
                    {(this.state.datePickerShown) ?
                        <DatePicker onDateChange={(startDate, endDate) => this.onDatesSelected(startDate, endDate)}/> :
                        <div/>}
                </div>
            </Navbar.Collapse>
        </Navbar>
    );

    toggleDatePicker() {
        this.setState({datePickerShown: !this.state.datePickerShown})
    }

    onDatesSelected(startDate, endDate) {
        this.props.onDateChange(startDate, endDate);
        this.setState({datePickerShown: false});
    }
}

export default Header;
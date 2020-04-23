import * as React from "react";
import {DateRange} from 'react-date-range';
import './date-picker.css';

export default class DatePicker extends React.Component {
    constructor(props) {
        super(props);
        this.whenDateChanged = this.whenDateChanged.bind(this);
    }

    render() {
        return (
            <div className="date-picker-window"><DateRange calendars={1} firstDayOfWeek={1}
                                                           onChange={this.whenDateChanged}/></div>
        );
    };

    whenDateChanged(date) {
        var endDate = date.endDate.format("YYYY-MM-DD");
        var startDate = date.startDate.format("YYYY-MM-DD");

        if (startDate !== endDate) {
            this.props.onDateChange(startDate, endDate);
        }
    }


}
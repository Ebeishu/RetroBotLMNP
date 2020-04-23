const MESSAGE_URL = "https://api.retrobot.grakovne.org/api/retrobot/history";

export default class MessageApi {

    fetchMessages(action, startDate, endDate) {
        fetch(this.buildMessageUrl(startDate, endDate))
            .then(response => response.json())
            .then(response => action(response))
    };


    buildMessageUrl(startDate, endDate) {
        if (startDate && endDate) {
            return MESSAGE_URL + "?from=" + startDate + "&to=" + endDate
        }

        return MESSAGE_URL;
    }
}
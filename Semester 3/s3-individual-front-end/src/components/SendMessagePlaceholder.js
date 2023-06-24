import { useState } from "react";
import '../css/QnASession.css';

const SendMessagePlaceholder = (props) => {
  const [message, setMessage] = useState('');

  const onMessageSend = (event) => {
    event.preventDefault();

    if (!message) {
      alert('Please type a message!');
      return;
    }

    props.onMessageSend({ 'text': message });
    setMessage('');
  }

  return (
    <form onSubmit={onMessageSend}>
      <input className="send-message" type='text' onChange={(e) => setMessage(e.target.value)} placeholder='Write a messsage' value={message}></input>
      <button className="send-message-button">Send</button>
    </form>
  );
}

export default SendMessagePlaceholder;
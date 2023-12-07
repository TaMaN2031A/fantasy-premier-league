import React, {useState} from "react";
import './SoccerTable.css';
import { fetchFaqData } from "../myServices/Faq_Rule/getFaqRuleData";

function Faq(props) {
    const [info, setInfo] = useState(props.faqData);

    return (
        <div className="table-container">
            <h3>FAQ</h3>
            <table className="soccer-table">
                <thead>
                <tr>
                    <th>Question</th>
                    <th>Answer</th>
                    <th>Date</th>
                </tr>
                </thead>
                <tbody>
                    {info.map((item, index) => (
                        <tr key={index}>
                            <td>{item.question}</td>
                            <td>{item.answer}</td>
                            <td>{item.date}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default Faq;
           


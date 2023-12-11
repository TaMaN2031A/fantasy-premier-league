import React, { useState, useEffect } from "react";
import './SoccerTable.css';
import {fetchFaqData} from "../../myServices/Faq_Rule";

async function Faq() {
    const [info, setInfo] = useState((fetchFaqData()) || []); // Use an empty array as default if props.faqData is undefined
    console.log(info)
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
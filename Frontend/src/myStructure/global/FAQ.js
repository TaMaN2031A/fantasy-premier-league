import React, {useEffect, useState} from "react";
import './SoccerTable.css';
import { fetchFaqData } from "../myServices/Faq_Rule/getFaqRuleData";

export const Faq = () => {    
    const [info, setInfo] = useState([]);
    useEffect(() => {
        const fetchData = async () => {
            try {
                const result = await fetchFaqData();
                setInfo(result);
                console.log("Got FAQ Successfully");
            } catch (err) {
                console.error(err);
            }
        };
        fetchData();
    }, []);
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
};
           


import React, { useState } from 'react';
import "./MatchStatistics.css"
import {addMatchStatistics} from "../../../Services/Match Statistics/AddMatchStatistics";
import { responses } from '../../../collection';
import {Link, useNavigate} from "react-router-dom";
import {paths, toastStyle, userPrivilege} from "../../../collection";
import {toast, ToastContainer} from "react-toastify";

function MatchStatistics(props) {
    const navigate = useNavigate();
    const[info , setInfo] = useState({
        homeTeam: "",
        awayTeam: "" , 
        manOfMatch :"",
        homePlayers:"" ,
        awayPlayers:"",
        homeGoals: "" ,
        awayGoals: "" , 
        homeYellowCards: "" , 
        awayYellowCards : "",
        homeRedCards : "",
        awayRedCards :"",
        homeAssists :"",
        awayAssists:"",
        homeSaves:"",
        awaySaves:""
    });


    function inputChange(e) {
        setInfo({ ...info, [e.target.name]: e.target.value });
    }
    
    function stringToList(e){
      return e.replace(/\n/g, ',').split(',').filter(item => item.trim() !== '');
    }

    const SubmitForm = async () => {
        

        // Create a JSON object
        var matchStatistics = {
          home: info.homeTeam,
          away: info.awayTeam,
          manOfMatch : info.manOfMatch,
          homePlayersPlayed: stringToList(info.homePlayers),
          awayPlayersPlayed: stringToList(info.awayPlayers),
          homePlayersScore: stringToList(info.homeGoals),
          awayPlayersScore: stringToList(info.awayGoals),
          homePlayersYellowCards: stringToList(info.homeYellowCards),
          awayPlayersYellowCards: stringToList(info.awayYellowCards),
          homePlayersRedCards: stringToList(info.homeRedCards),
          awayPlayersRedCards: stringToList(info.awayRedCards),
          homePlayersAssist:stringToList(info.homeAssists),
          awayPlayersAssist: stringToList(info.awayAssists),
          homePlayersSaves : stringToList(info.homeSaves),
          awayPlayersSaves : stringToList(info.awaySaves),
        };
        console.log(matchStatistics);
  
        // Display the result (you can send this data to the server or perform further actions)
        
        let response = await addMatchStatistics(matchStatistics);
        if(response == responses.MatchAddedSuccessfully){
            toast.success(response, toastStyle);
            navigate(paths.home);
        }else{
            toast.error(response, toastStyle);
        }
        // Prevent the form from actually submitting (for this example)
        return false;
      }
    
    return (
        <div className="py-4 bg-gradient-to-r from-slate-800 to-gray-900 min-h-screen">
      <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 flex flex-col justify-between">
        {/* header */}
        <ToastContainer/>
        <div className="text-center">
          <p></p>
          <h3 className="border-b-2 mt-10 mb-20 sm:text-4xl leading-normal font-extrabold tracking-tight text-white">
            Add Match Statistics
          </h3>
        </div>
        <div className="mt-20 mb-12">
        
            
                <h2 className='text-white'> Enter Match Statistics</h2>

                
                <label id ="labelMatch" for="homeTeam" className='text-white'>Home Team:</label>
                <input type="text" id="inputMatch" name="homeTeam"  value={info.homeTeam} onChange={inputChange} required/>

                
                <label id ="labelMatch" for="awayTeam" className='text-white'>Away Team:</label>
                <input type="text" id="inputMatch" name="awayTeam" value={info.awayTeam} onChange={inputChange} required/>

                <label id ="labelMatch" for="playerOfMatch" className='text-white'>Player of the match:</label>
                <input type="text" id="inputMatch" name="manOfMatch" value={info.manOfMatch} onChange={inputChange} required/>

                
                <h3 className='text-white'>Players</h3>
                
                <label id ="labelMatch" for="homePlayers" className='text-white' >Home Team Players (comma-separated):</label>
                <textarea id="textareaMatch" type="text"  name="homePlayers" value={info.homePlayers}
                                        onChange={inputChange} required/>

                <label id ="labelMatch" for="awayPlayers" className='text-white' >Away Team Players (comma-separated):</label>
                <textarea id="textareaMatch" type="text"  name="awayPlayers" value={info.awayPlayers}
                                        onChange={inputChange} required/>

                
                <h3 className='text-white' >Goals</h3>

                <label id ="labelMatch" for="homeGoals" className='text-white'>Home Team Goals (comma-separated player names):</label>
                <textarea id="textareaMatch" type="text"  name="homeGoals" value={info.homeGoals}
                                        onChange={inputChange} required />

                <label id ="labelMatch" for="awayGoals" className='text-white'>Away Team Goals (comma-separated player names):</label>
                <textarea id="textareaMatch" type="text"  name="awayGoals" value={info.awayGoals}
                                        onChange={inputChange} required/>

                
                <h3 className='text-white'>Yellow Cards</h3>

                <label id ="labelMatch" for="homeYellowCards" className='text-white' >Home Team Yellow Cards (comma-separated player names):</label>
                <textarea id="textareaMatch" type="text"  name="homeYellowCards" value={info.homeYellowCards}
                                        onChange={inputChange} required/>

                <label id ="labelMatch" for="awayYellowCards" className='text-white' >Away Team Yellow Cards (comma-separated player names):</label>
                <textarea id="textareaMatch" type="text"  name="awayYellowCards" value={info.awayYellowCards}
                                        onChange={inputChange} required/>

                
                <h3 className='text-white' >Red Cards</h3>

                <label id ="labelMatch" for="homeRedCards" className='text-white'>Home Team Red Cards (comma-separated player names):</label>
                <textarea id="textareaMatch" type="text"  name="homeRedCards" value={info.homeRedCards}
                                        onChange={inputChange} required/>

                <label id ="labelMatch" for="awayRedCards" className='text-white'  >Away Team Red Cards (comma-separated player names):</label>
                <textarea id="textareaMatch" type="text"  name="awayRedCards" value={info.awayRedCards}
                                        onChange={inputChange} required/>

                <h3 className='text-white' >Assists</h3>

                <label id ="labelMatch" for="homeAssists" className='text-white'>Home Team Assists (comma-separated player names):</label>
                <textarea id="textareaMatch" type="text"  name="homeAssists" value={info.homeAssists}
                                        onChange={inputChange} required/>

                <label id ="labelMatch" for="awayAssists" className='text-white'  >Away Team Assists (comma-separated player names):</label>
                <textarea id="textareaMatch" type="text"  name="awayAssists" value={info.awayAssists}
                                        onChange={inputChange} required/>

                <h3 className='text-white' >Saves</h3>

                <label id ="labelMatch" for="homeSaves" className='text-white'>Home Team Saves (comma-separated player names):</label>
                <textarea id="textareaMatch" type="text"  name="homeSaves" value={info.homeSaves}
                                        onChange={inputChange} required/>

                <label id ="labelMatch" for="awaySaves" className='text-white'  >Away Team Red Cards (comma-separated player names):</label>
                <textarea id="textareaMatch"  type="text" name="awaySaves" value={info.awaySaves}
                                        onChange={inputChange} required/>
                
                <button id="submitMatch" type="button" className='text-white' onClick={SubmitForm}>Submit</button>
            

        </div>
      </div>
    </div>
  );

}

export default MatchStatistics;
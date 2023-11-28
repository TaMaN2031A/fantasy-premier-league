
import './App.css';
import {BrowserRouter, Routes, Route, Link} from 'react-router-dom';
import Wrapper from "./myStructure/wrapper";
function App() {
    return (
        <BrowserRouter>
            <Wrapper />
        </BrowserRouter>
    );
}

export default App;

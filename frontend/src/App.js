import React, {useState} from 'react'
import {BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import Form from './components/Form';
import Preview from './components/Preview';

function App() {


  const [name, setName] = useState('')
  const [url, setUrl] = useState('')


  return (
    <div className="App">
      
      <Router>
     
           
              <Routes>
     
              <Route exact path = "/form" element= {<Form name={name} setName = {setName} url={url} setUrl={setUrl}/>}></Route>
              <Route  path = "/preview" element= {<Preview name={name} setName = {setName} url={url} setUrl={setUrl}/>}></Route>
            
              </Routes>
      </Router>

    </div>
  );
}

export default App;

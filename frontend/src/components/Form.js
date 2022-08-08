import React from 'react'
import { useNavigate} from 'react-router-dom';

import './Form.css'
import './Normalize.css'

import axios from 'axios'

function Form(args) {



  const name=args.name; 
  const setName=args.setName;
  const url=args.url; 
  const setUrl=args.setUrl;
  const nav = useNavigate ();

// const [isvalid, setIsValid] = useState(false);



 const onsubmit =(e)=>{
    e.preventDefault();
    const org={name,url}
    // console.log(org);
    axios.post(`http://localhost:8080/add/org`,org)
    .then(req => {
      console.log(req);
      console.log(req.data);
    })
    
    console.log(e);
  }
  
  const handleclick =(e)=>{
      e.preventDefault();
      console.log(e);
    nav("/preview");

  }
  return (
    <div className="container">
        
         <div className = " bckimg">
                           <h2 className='hed'>Fill the form to see the preview</h2>
                           <div className='place'>
                            <form  className='place' onSubmit={onsubmit}>
                            
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Full Name :</label>
                                    <input
                                        type = "text"
                                        placeholder = "Enter Full name"
                                        name = "name"
                                        className = "form-control"
                                        value = {name}
                                        required
                                        onChange = {(e) => setName(e.target.value)}
                                       
                                    >
                                    </input>
                         
                                </div> <br></br>




                                <div className = "form-group mb-2">
                                

                                    <label className = "form-label"> Logo url:</label>
                                    <input
                                        type = "text"
                                        placeholder = "Enter Logo url"
                                        name = "url"
                                        className = "form-control"
                                        value = {url}
                                        required
                                        onChange = {(e) => setUrl(e.target.value)}
                                        />
                                        </div><br></br>
                                        <div>
                                <button  className = "submit" onSubmit={onsubmit} >Submit </button>
                                <button  className = "submit" onClick={handleclick}  >See preview </button>
                              
                              </div>
                             
                                </form> 
                                </div>
                                </div>






                       


       </div>
       
 )
}

export default Form

import React from 'react'
import { Button } from 'react-bootstrap';
import './Form.css'


function Preview(props) {



  const name=props.name; 

  const url=props.url; 

  return (
    <div className='back'>
           <div >
                                  <h2> See the preview here</h2>
            </div>
                              
                                <div>
                                        <img  src={url} alt=""/>
                               </div>
                                 
                                    <h1 className='label cont'>welcome Aboard</h1>
                                
                                  <div className='left'>
                                    <h3 className='yellow'>Dear user</h3>
                                    <br></br>
                                    <h4>welcome to {name}</h4>
                                 
                                    <p>We&apos;re
													really excited
													you&apos;ve
													decided to give
													us a try. In
													case you have
													any questions,
													feel free to
													reach out to us
													at
													freshworks@gmail.com
													You can login to
													your account
													with your
													username (e.g {name}@123).
                          And can use the below link to activate your account.
                              </p>
                          
                              <Button className='login' >Login</Button>
                              <br></br>
                              <br></br>

                              <div>
                              Thanks, <br />
                              <p>	The  {' '}
												{name} {' '}
													Team
                          </p> 
                              </div>
               </div>
                                 

             
       </div>
                                   
  
  )
}

export default Preview

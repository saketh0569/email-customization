import * as React from 'react';
import { useState } from 'react';
import { TextField, Button, Typography, Box, Paper, Stack, Alert, Grid, ListItem } from '@mui/material';
import { useNavigate } from 'react-router-dom';

export default function AddOrg() {
        const [name, setName] = useState('')
        const [url, setUrl] = useState('')

        const navigate = useNavigate();


        const handleClick = (e) => {
                e.preventDefault();
                const org = { name, url };
                var a = "", b = "";
                if (name.length < 2)
                        a = "Org name should be 2 to 20 characters. ";
                if (url.length == 0)
                        b = "URL should not be empty. "

                if (a.length == 0 && b.length == 0) {
                        console.log(org);
                        fetch("http://localhost:8080/org/add", {
                                method: "POST",
                                headers: { "Content-Type": "application/json" },
                                body: JSON.stringify(org)
                        }).then(() => {
                                alert("New Org added");
                                // <Alert severity="success">New Movie Added</Alert>
                                navigate("/");
                        })
                }
                else {
                        // <Alert severity="success">{a + b + c}</Alert>
                        alert(a + b);
                }
        }

        return (
                <>
                        <Grid container spacing={2}>
                                <Grid item xs={8}>
                                        <ListItem>xs=6</ListItem>
                                        <Paper component="form" elevation={3} spacing={5} style={{ margin: "10% 33%" }}>
                                                <Box style={{ padding: "5%" }}>
                                                        <Typography variant='h3'>Add your org</Typography>
                                                </Box>
                                                <Stack style={{ padding: "30px" }}>

                                                        <TextField id="filled-basic" label="Org Name" variant="filled" fullWidth
                                                                value={name} onChange={(e) => setName(e.target.value)} />
                                                        <TextField id="filled-basic" label="url" variant="filled" fullWidth
                                                                value={url} onChange={(e) => setUrl(e.target.value)} />
                                                        <br />
                                                        <Button variant="contained" onClick={handleClick}>
                                                                Submit
                                                        </Button>
                                                </Stack>
                                        </Paper>
                                </Grid>
                                <Grid item xs={4}>
                                        <ListItem>xs=6</ListItem>
                                        <div className='grow1 back'>
                                                <div >
                                                        <div className='hed'>
                                                                <h2> See the preview here</h2>
                                                        </div>
                                                        <div className='back'>
                                                                <div>
                                                                        <img src={url} alt="" />
                                                                </div>
                                                                <div className='cont'>
                                                                        <h1 className='label'>welcome Aboard</h1>
                                                                </div>
                                                                <div className='left '>
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
                                                                                username (e.g {name}@123)
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
                                                </div>


                                        </div>
                                </Grid>
                        </Grid>
                </>

        );
}

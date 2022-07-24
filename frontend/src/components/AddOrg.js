import * as React from 'react';
import { useState } from 'react';
import { TextField, Button, Typography, Box, Paper, Stack, Alert } from '@mui/material';
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
                <Paper component="form" elevation={3} spacing={5} style={{ margin: "8% 35%" }}>
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
        );
}

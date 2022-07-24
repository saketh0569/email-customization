import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import { Button } from '@mui/material';
import { Link } from "react-router-dom";
import { useEffect, useState } from 'react';
import { AccountCircleOutlined } from '@mui/icons-material';
import HomeIcon from '@mui/icons-material/Home'

export default function Appbar() {

        return (
                <Box sx={{ flexGrow: 1 }}>
                        <AppBar position="static">
                                <Toolbar>
                                        <Button color="inherit">
                                                <Link to="/" style={{ color: 'white', textDecoration: 'none' }}>
                                                        <HomeIcon fontSize='large' />
                                                        {/* MRS */}
                                                </Link>
                                        </Button>
                                        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                                                email cust
                                        </Typography>
                                        <Button color="inherit">
                                                <Link to="/login" style={{ color: 'white', textDecoration: 'none' }}>
                                                        Login
                                                </Link>
                                        </Button>
                                </Toolbar>
                        </AppBar>
                </Box>
        );
}

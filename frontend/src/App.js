import './App.css';
import AddOrg from './components/AddOrg';
import Appbar from './components/Appbar';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

function App() {
        return (
                <Router>
                        <Appbar />
                        <div className="App">
                                <Routes>
                                        <Route path="/addorg" element={<AddOrg />} />
                                </Routes>
                        </div>
                </Router>
        );
}

export default App;

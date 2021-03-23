import { Component } from 'react';
import './nav-bar.css';

class NavBar extends Component{

    render(){
        return(
            <div>
                <div className="nav-bar-texts">
                    <button className="empty-button"><span onClick= {() => { window.location.href = '/planning-dashboard';}}>
                    Planning Dashboard</span></button>
                    <button className="empty-button"><span className="nav-bar-style" onClick= {() => {
                        window.location.href = '/leaves-dashboard';}}>
                    Leaves Dashboard</span></button>
                </div>
            </div>
        );
    }
}

export default NavBar;
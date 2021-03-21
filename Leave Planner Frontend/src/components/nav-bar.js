import { Component } from 'react';
import './nav-bar.css';

class NavBar extends Component{
    
    /*constructor() {
        super();
        this.state = {
           clicked: "Planning"
        }
        this.handleClick = this.handleClick.bind(this);
    };*/

    render(){
        return(
            <div>
                <div className="nav-bar-texts">
                    <span onClick= {() => { window.location.href = '/planning-dashboard';}}>
                    Planning Dashboard</span>
                    <span className="nav-bar-style" onClick= {() => {
                        window.location.href = '/leaves-dashboard';}}>
                    Leaves Dashboard</span>
                </div>
            </div>
        );
    }
}

export default NavBar;
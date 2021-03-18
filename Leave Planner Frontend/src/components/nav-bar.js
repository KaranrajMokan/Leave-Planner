import { Component } from 'react';
import './nav-bar.css';

class NavBar extends Component{
    constructor() {
        super();
        this.state = {
           clicked: "Empty"
        }
    };
  
    render(){
        return(
            <div>
                <div className="nav-bar-texts">
                    <span onClick= {() => this.setState({ clicked:'planning'})}>Planning Dashboard</span>
                    <span className="nav-bar-style" onClick= {() => this.setState({ clicked:'leaves'})}>
                    Leaves Dashboard
                    </span>
                </div>
            </div>
        );
    }
}

export default NavBar;
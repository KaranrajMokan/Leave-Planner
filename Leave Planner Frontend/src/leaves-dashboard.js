import { Component } from 'react';

import Logo from './images/LP-logo.png';
import NavBar from './components/nav-bar';

class LeavesDashboard extends Component{

    constructor(props){
        super(props);
        this.logoutFunction = this.logoutFunction.bind(this);
    }

    logoutFunction(){
        localStorage.clear();
        window.location.href = '/';
    }

    render(){
        const halfName = "Hello, "+JSON.parse(localStorage.getItem("studentToken")).name;
        const displayName = <div className="positions end-texts">{halfName}</div>;
        return(
        <div>
            <img className="image-div" src={Logo} alt=""></img>
                <div className="app-lines change-speed"></div>
                <div><NavBar />{displayName}<button className="logout-but end-texts" onClick={this.logoutFunction}>LOGOUT</button></div>
                <div className="line-div">
                    <div className="inner-div">

                    </div>
                </div>
        </div>);
    }
}

export default LeavesDashboard;
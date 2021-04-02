import { Component } from 'react';
import './leaves-dashboard.css';
import Logo from './images/LP-logo.png';
import NavBar from './components/nav-bar';
import axios from 'axios';

var upcomingLeaveDetails={};
var days = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
class LeavesDashboard extends Component{

    constructor(props){
        super(props);
        this.logoutFunction = this.logoutFunction.bind(this);
        this.getUpcomingLeaves = this.getUpcomingLeaves(this);
    }

    logoutFunction(){
        localStorage.clear();
        window.location.href = '/';
    }

    getUpcomingLeaves(){
        const details = JSON.parse(localStorage.getItem("studentToken"));
        const requestParameters = {
            rollNumber : details.rollNumber,
            token : details.token
        }
        axios.request({
            method:'post',
            url:'http://localhost:8080/upcoming-leaves',
            data: requestParameters
        }).then(response=> {
            console.log(response.data);
            for(var i=0;i<response.data.length;i++){
                upcomingLeaveDetails[i]=response.data[i];
            }
        }).catch(error => {
            console.log(error.response);
        });
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
                        <div className="leaves-rectangle"> 
                            <div className="texts size1 div1">Upcoming leaves</div>
                            <div className="lines-new"></div>
                        </div>
                    </div>
                </div>
        </div>);
    }
}

export default LeavesDashboard;
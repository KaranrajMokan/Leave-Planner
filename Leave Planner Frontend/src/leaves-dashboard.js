import { Component } from 'react';
import './leaves-dashboard.css';
import Logo from './images/LP-logo.png';
import NavBar from './components/nav-bar';
import axios from 'axios';
import NoUpcomingLeaves from './images/bull-upcoming.png'

var days = ['Sun','Mon','Tue','Wed','Thu','Fri','Sat'];
var month = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
var displayLeaves=[];
var FontAwesome = require('react-fontawesome');
class LeavesDashboard extends Component{

    constructor(props){
        super(props);
        this.logoutFunction = this.logoutFunction.bind(this);
        this.getUpcomingLeaves = this.getUpcomingLeaves(this);
        this.state = {
            data : ""
        }
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
            // for(var i=0;i<response.data.length;i++){
            //     upcomingLeaveDetails.push(response.data[i]);
            // }
            this.setState({data: response.data});
        }).catch(error => {
            console.log(error.response);
        });
    }

    
    render(){
        const halfName = "Hello, "+JSON.parse(localStorage.getItem("studentToken")).name;
        const displayName = <div className="positions end-texts">{halfName}</div>;
        if(this.state.data.length > 0){
            displayLeaves.pop()
            for(var i=0;i<this.state.data.length;i++){
                var leave = this.state.data[i];
                if (leave.leaveDuration === 1){
                    var dayOfWeek = new Date(leave.leaveStartDate).getDay();
                    var dates;
                    if(dayOfWeek !== 0 && dayOfWeek !== 6){
                        dates = new Date(leave.leaveStartDate);
                    }
                    else{
                        dates = new Date(leave.leaveEndDate);
                    }
                    displayLeaves.push(<div className="total-aligns">
                    <FontAwesome className="icon-styles" name="calendar"/><span className="big-text">{month[dates.getMonth()]} {dates.getDate()} </span>
                    <span className="small-text">{days[dates.getDay()]}</span> <button id={leave.leaveId} className="empty-button edit-styles-one"> <FontAwesome name="edit"/></button> 
                    <button id={leave.leaveId} className="empty-button trash-styles-one"><FontAwesome name="trash"/></button> 
                    <div className="downtown-texts"> {leave.leaveDuration} day {leave.leaveType.toLowerCase()}</div>
                    <div className="lines-new pad-lines-new"></div>
                    </div>);
                }
                else{
                    var daysOfWeek = new Date(leave.leaveStartDate).getDay();
                    var startDate;
                    var endDate;
                    if(daysOfWeek !== 0 && daysOfWeek !== 6){
                        startDate = new Date(leave.leaveStartDate);
                    }
                    else{
                        startDate = new Date(leave.leaveStartDate);
                        if(daysOfWeek === 0)
                            startDate.setDate(startDate.getDate()+1);
                        else if(daysOfWeek === 6)
                            startDate.setDate(startDate.getDate()+2);
                    }
                    daysOfWeek = new Date(leave.leaveEndDate).getDay();
                    if(daysOfWeek !== 0 && daysOfWeek !== 6){
                        endDate = new Date(leave.leaveEndDate);
                    }
                    else{
                        endDate = new Date(leave.leaveEndDate);
                        if(daysOfWeek === 0)
                            endDate.setDate(endDate.getDate()-2);
                        else if(daysOfWeek === 6)
                            endDate.setDate(endDate.getDate()-1);
                    }
                    displayLeaves.push(<div className="total-aligns">
                    <FontAwesome className="icon-styles" name="calendar"/><span className="big-text">{month[startDate.getMonth()]} {startDate.getDate()} </span>
                    <span className="small-text">{days[startDate.getDay()]}</span><span className="big-text-hyphen"> - </span><span className="big-text">{month[endDate.getMonth()]} {endDate.getDate()} </span>
                    <span className="small-text">{days[endDate.getDay()]}</span> <button id={leave.leaveId} className="empty-button edit-styles"> <FontAwesome name="edit"/></button> 
                    <button id={leave.leaveId} className="empty-button trash-styles"><FontAwesome name="trash"/></button> 
                    <div className="downtown-texts"> {leave.leaveDuration} day {leave.leaveType.toLowerCase()}s</div>
                    <div className="lines-new pad-lines-new"></div>
                    </div>);
                }
            }
        }
        else{
            if(displayLeaves.length === 0)
                displayLeaves.push(<img className="img-loc" src={NoUpcomingLeaves} alt="No upcoming leaves"></img>);
        }
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
                            <div className="total-aligns">{displayLeaves}</div> 
                        </div>
                    </div>
                </div>
        </div>);
    }
}

export default LeavesDashboard;
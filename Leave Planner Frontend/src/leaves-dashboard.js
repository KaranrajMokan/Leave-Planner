import { Component } from 'react';
import './leaves-dashboard.css';
import Logo from './images/LP-logo.png';
import NavBar from './components/nav-bar';
import axios from 'axios';
import NoUpcomingLeaves from './images/bull-upcoming.png'
import NoLeavesHistory from './images/bull-history.png'
import Toast from './components/toast';
import checkIcon from './images/check_icon.png';

var days = ['Sun','Mon','Tue','Wed','Thu','Fri','Sat'];
var month = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
var displayUpcomingLeaves=[];
var displayPastLeaves=[];
var toaster;
var FontAwesome = require('react-fontawesome');
class LeavesDashboard extends Component{

    constructor(props){
        super(props);
        this.logoutFunction = this.logoutFunction.bind(this);
        this.getUpcomingLeaves = this.getUpcomingLeaves(this);
        this.getPastLeaves = this.getPastLeaves(this);
        this.computeAndDisplayUpcomingLeaves = this.computeAndDisplayUpcomingLeaves.bind(this);
        this.computeAndDisplayPastLeaves = this.computeAndDisplayPastLeaves.bind(this);
        this.handleEditLeaves = this.handleEditLeaves.bind(this);
        this.handleDeleteLeaves = this.handleDeleteLeaves.bind(this);
        this.state = {
            upcomingLeaveData : "",
            pastLeaveData : "",
            isLeaveDeleted : false
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
            this.setState({upcomingLeaveData: response.data});
        }).catch(error => {
            console.log(error.response);
        });
    }

    getPastLeaves(){
        const details = JSON.parse(localStorage.getItem("studentToken"));
        const requestParameters = {
            rollNumber : details.rollNumber,
            token : details.token
        }
        axios.request({
            method:'post',
            url:'http://localhost:8080/leaves-history',
            data: requestParameters
        }).then(response=> {
            console.log(response.data);
            this.setState({pastLeaveData: response.data});
        }).catch(error => {
            console.log(error.response);
        });
    }

    computeAndDisplayUpcomingLeaves(){
        if(this.state.upcomingLeaveData.length > 0){
            displayUpcomingLeaves=[]
            for(var i=0;i<this.state.upcomingLeaveData.length;i++){
                var leave = this.state.upcomingLeaveData[i];
                if (leave.leaveDuration === 1){
                    var dayOfWeek = new Date(leave.leaveStartDate).getDay();
                    var dates;
                    if(dayOfWeek !== 0 && dayOfWeek !== 6){
                        dates = new Date(leave.leaveStartDate);
                    }
                    else{
                        dates = new Date(leave.leaveEndDate);
                    }
                    displayUpcomingLeaves.push(<div className="total-aligns-new">
                    <FontAwesome className="icon-styles" name="calendar"/><span className="big-text">{month[dates.getMonth()]} {dates.getDate()} </span>
                    <span className="small-text">{days[dates.getDay()]}</span> <button onClick={this.handleEditLeaves} className="empty-button edit-styles"><FontAwesome id={leave.leaveId} name="edit"/></button> 
                    <button onClick={this.handleDeleteLeaves} className="empty-button trash-styles"><FontAwesome id={leave.leaveId} name="trash"/></button> 
                    <div className="downtown-texts"> {leave.leaveDuration} day {leave.leaveType}</div>
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
                    displayUpcomingLeaves.push(<div className="total-aligns-new">
                    <FontAwesome className="icon-styles" name="calendar"/><span className="big-text">{month[startDate.getMonth()]} {startDate.getDate()} </span>
                    <span className="small-text">{days[startDate.getDay()]}</span><span className="big-text-hyphen"> - </span><span className="big-text">{month[endDate.getMonth()]} {endDate.getDate()} </span>
                    <span className="small-text">{days[endDate.getDay()]}</span> <button onClick={this.handleEditLeaves} className="empty-button edit-styles"> <FontAwesome id={leave.leaveId} name="edit"/></button> 
                    <button onClick={this.handleDeleteLeaves} className="empty-button trash-styles"><FontAwesome id={leave.leaveId} name="trash"/></button> 
                    <div className="downtown-texts"> {leave.leaveDuration} day {leave.leaveType}s</div>
                    <div className="lines-new pad-lines-new"></div>
                    </div>);
                }
            }
        }        
        else{
            if(displayUpcomingLeaves.length === 0)
                displayUpcomingLeaves.push(<img className="img-loc" src={NoUpcomingLeaves} alt="No upcoming leaves"></img>);
        }
    }

    computeAndDisplayPastLeaves(){
        if(this.state.pastLeaveData.length > 0){
            displayPastLeaves=[]
            for(var i=0;i<this.state.pastLeaveData.length;i++){
                var divider;
                if(i !== this.state.pastLeaveData.length-1)
                    divider=<div className="conjunction"></div>;
                else
                    divider="";
                var leave = this.state.pastLeaveData[i];
                if (leave.leaveDuration === 1){
                    var dayOfWeek = new Date(leave.leaveStartDate).getDay();
                    var dates;
                    if(dayOfWeek !== 0 && dayOfWeek !== 6){
                        dates = new Date(leave.leaveStartDate);
                    }
                    else{
                        dates = new Date(leave.leaveEndDate);
                    }
                    displayPastLeaves.push(<div className="total-aligns-new-history">
                        <div className="small-box">
                            <div className="dates">{month[dates.getMonth()]} {dates.getDate()}</div>
                            <div className="single-leaveType">{leave.leaveType}</div>
                        </div>
                        <div className="divider">{divider}</div>
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
                    displayPastLeaves.push(<div key={leave.leaveId} className="total-aligns-new-history">
                        <div className="big-box">
                            <div className="startDate">{month[startDate.getMonth()]} {startDate.getDate()}</div>
                            <div className="endDate">{month[endDate.getMonth()]} {endDate.getDate()}</div>
                            <div className="leaveType">{leave.leaveType}s</div> 
                            <div className="leaveDuration">({leave.leaveDuration} days)</div>
                        </div>
                        <div className="divider">{divider}</div>
                    </div>);
                }
            }
        }        
        else{
            if(displayPastLeaves.length === 0)
                displayPastLeaves.push(<img className="img-loc" src={NoLeavesHistory} alt="No upcoming leaves"></img>);
        }
    }

    handleEditLeaves(e){
        const editLeave = this.state.upcomingLeaveData.filter(leave => leave.leaveId === e.target.id);
        console.log(editLeave);
    }

    handleDeleteLeaves(e){
        const deleteLeave = this.state.upcomingLeaveData.filter(leave => leave.leaveId === e.target.id);
        console.log(deleteLeave);
        var answer = window.confirm("Are you sure you want to delete this leave?")
        if(answer){
            const details = JSON.parse(localStorage.getItem("studentToken"));
            const requestParameters = {
                leaveId : e.target.id,
                rollNumber : details.rollNumber,
                token : details.token
            }
            axios.request({
                method:'delete',
                url:'http://localhost:8080/delete-leaves',
                data: requestParameters
            }).then(response=> {
                console.log(response.data);
                this.setState({isLeaveDeleted: true});
            }).catch(error => {
                console.log(error.response);
            });
        }
    }

    render(){
        const halfName = "Hello, "+JSON.parse(localStorage.getItem("studentToken")).name;
        const displayName = <div className="positions end-texts">{halfName}</div>;
        this.computeAndDisplayUpcomingLeaves();
        this.computeAndDisplayPastLeaves();
        if(this.state.isLeaveDeleted){
            var toastMessage =
            {
                title: 'Success',
                description: 'Leave is deleted successfully',
                backgroundColor: '#5cb85c',
                icon: checkIcon
            };
            toaster = <Toast toast={toastMessage} page="leaves-dashboard"/>;   
        }
        return(
        <div>
            {toaster}
            <img className="image-div" src={Logo} alt=""></img>
                <div className="app-lines change-speed"></div>
                <div><NavBar />{displayName}<button className="logout-but end-texts" onClick={this.logoutFunction}>LOGOUT</button></div>
                <div className="line-div">
                    <div className="inner-div">
                        <div className="leaves-rectangle1"> 
                            <div className="texts size1 div1">Upcoming leaves</div>
                            <div className="lines-new"></div>
                                <div className="total-aligns leaves-scroll">{displayUpcomingLeaves}</div> 
                        </div>
                        <div className="leaves-rectangle2">
                            <div className="texts size1 div1">Leaves History</div>
                            <div className="lines-new"></div>
                                <div className="adjustment leaves-scroll">{displayPastLeaves}</div>
                        </div>
                    </div>
                </div>
        </div>);
    }
}

export default LeavesDashboard;
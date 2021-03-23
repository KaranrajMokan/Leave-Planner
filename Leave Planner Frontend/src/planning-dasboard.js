import { Component } from 'react';
import './planning-dashboard.css';
import axios from 'axios';

import Logo from './images/LP-logo.png';
import NavBar from './components/nav-bar';

import Select from 'react-select';
import Toast from './components/toast';
import checkIcon from './images/check_icon.png';
import errorIcon from './images/error_icon.png';

//import DatePicker from "react-datepicker";
//import { FontAwesome } from 'react-fontawesome';

var rollNumber;
var name;
var toastMessage="";
var toaster;
class PlanningDashboard extends Component{

    constructor(props){
        super(props);
        this.state ={
            leaveType: '',
            isPlanned: false,
            endDateLimit : new Date().toISOString().split('T')[0],
            leaveMessage: ''
        };
        this.onSubmit = this.onSubmit.bind(this);
        this.planLeavesFunction = this.planLeavesFunction.bind(this);
        this.getDropDownValue = this.getDropDownValue.bind(this);
        this.resetForm = this.resetForm.bind(this);
        this.logoutFunction = this.logoutFunction.bind(this);
        this.setEndLimit = this.setEndLimit.bind(this);
    }

    planLeavesFunction(leavesList){
        axios.request({
            method:'post',
            url:'http://localhost:8080/leave-details',
            data: leavesList
        }).then(response => {
            console.log(response.data);
        }).catch(error => {
            console.log(error.response);
        });
    }

    onSubmit(e){
        const leaveDetails ={
            rollNumber : rollNumber,
            leaveType : this.state.leaveType.value,
            startDate : this.refs.startDate.value,
            endDate : this.refs.endDate.value,
            emailId : this.refs.email.value,
            studentToken : JSON.parse(localStorage.getItem("studentToken")).token
        };
        if (leaveDetails.leaveType !== '' && leaveDetails.startDate !== '' && leaveDetails.endDate !== '' && leaveDetails.emailId !== ''){
            this.planLeavesFunction(leaveDetails);
            this.resetForm();
            this.setState({isPlanned:true});
        }
        else {
            this.setState({leaveMessage:"Missing inputs"});
        }
        e.preventDefault();
    }

    getDropDownValue = leaveType => {
        this.setState({leaveType});
    }

    resetForm(){
        document.getElementById("planning-tab").reset();
        this.setState({leaveType:''});
        this.setState({endDateLimit: new Date().toISOString().split('T')[0]});
    }

    logoutFunction(){
        localStorage.clear();
        window.location.href = '/';
    }

    setEndLimit(){
        this.setState({endDateLimit: document.getElementById("startDate").value});
    }

    render(){
        const {leaveType} = this.state;
        const leaves = [
            {value: 'Casual Leave', label:'Casual Leave'},
            {value: 'Sick Leave', label:'Sick Leave'}
        ];
        rollNumber = JSON.parse(localStorage.getItem("studentToken")).rollNumber;
        name = JSON.parse(localStorage.getItem("studentToken")).name;
        const halfName = "Hello, "+name;
        const displayName = <div className="positions end-texts">{halfName}</div>;
        if(this.state.isPlanned) {
                toastMessage =
                {
                    title: 'Success',
                    description: 'Leave is planned successfully',
                    backgroundColor: '#5cb85c',
                    icon: checkIcon
                };
                toaster = <Toast toast={toastMessage} page="planning-dashboard"/>;
        }
        else{
            if (this.state.leaveMessage !== ""){
                toastMessage =
                {
                    title: 'Failure',
                    description: "Missing inputs",
                    backgroundColor: '#d9534f',
                    icon: errorIcon
                };  
                toaster = <Toast toast={toastMessage} page="planning-dashboard"/>;
            }
        }
        return(
            <div>
                {toaster}
                <img className="image-div" src={Logo} alt=""></img>
                <div className="app-lines"></div>
                <div><NavBar />{displayName}<button className="logout-but end-texts" onClick={this.logoutFunction}>LOGOUT</button></div>
                <div className="line-div">
                    <div className="inner-div">
                        <div className="rectangle"> 
                            <div className="texts size1 div1">Plan leaves</div>
                            <div className="lines-new"></div>
                            <div className="texts size2 div2">Roll No &nbsp;&nbsp;{rollNumber}</div>
                            <div className="texts size2 div3">Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{name}</div>
                            <form id="planning-tab" onSubmit={this.onSubmit}> 
                                <div className="texts size2 div1">Leave type &nbsp;<span className="red-color">*</span></div>
                                <Select className="boxes dropdown-texts" options={leaves} onChange={this.getDropDownValue} value={leaveType} placeholder="Select a leave type"/>
                                <div className="texts size2 div1">Start Date&nbsp;<span className="red-color">*</span></div>
                                <div className="boxes"><input type="date" id="startDate" min={new Date().toISOString().split('T')[0]} onChange={this.setEndLimit} className="form-control" ref="startDate"/></div>
                                <div className="texts size2 div4">End Date&nbsp;<span className="red-color">*</span></div>
                                <div className="boxes"><input type="date" min={this.state.endDateLimit} className="form-control" ref="endDate"/></div>
                                <div className="texts size2 div4">Send leave notifications to &nbsp;<span className="red-color">*</span></div>
                                <div className="boxes"><input type="text" className="form-control" ref="email"/></div>
                                <p className="texts div5">Use comma seperated values (not spaces)</p>
                                <button type="button"className="button1" onClick={this.resetForm}>Reset</button> 
                                <button type="submit" className="button2">Add to Plan</button>
                            </form>
                            <p className="texts div6"><span className="red-color">*</span>Mandatory</p>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default PlanningDashboard;
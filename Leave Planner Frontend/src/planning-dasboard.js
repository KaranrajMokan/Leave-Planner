import { Component } from 'react';
import './planning-dashboard.css';
import axios from 'axios';

import Logo from './images/LP-logo.png';
import NavBar from './components/nav-bar';

import Select from 'react-select';
import Toast from './components/toast';
import checkIcon from './images/check_icon.png';
import errorIcon from './images/error_icon.png';
import CustomDatePicker from './components/customDatePicker';

var rollNumber;
var name;
var toastMessage="";
var toaster;
class PlanningDashboard extends Component{

    constructor(props){
        super(props);
        this.state ={
            leaveType: '',
            isPlanned: 0,
            endDateLimit : new Date().toISOString().split('T')[0],
            leaveMessage: '',
            editLeaveId : '',
            editLeaveType : ''
        };
        this.onSubmit = this.onSubmit.bind(this);
        this.planLeavesFunction = this.planLeavesFunction.bind(this);
        this.getDropDownValue = this.getDropDownValue.bind(this);
        this.resetForm = this.resetForm.bind(this);
        this.logoutFunction = this.logoutFunction.bind(this);
        this.setEndLimit = this.setEndLimit.bind(this);
    }

    componentDidMount(){
        if(typeof this.props.location.state !== 'undefined'){
            var leaveDetails = this.props.location.state.leaveDetails;
            var newLeaves = leaveDetails[0];
            console.log(newLeaves);
            this.setState({editLeaveId : newLeaves.leaveId});
            this.setState({leaveType : {value : newLeaves.leaveType, label : newLeaves.leaveType}});
            this.setState({endDateLimit: newLeaves.leaveStartDate});
            document.getElementById("startDate").defaultValue = newLeaves.leaveStartDate;
            document.getElementById("endDate").defaultValue = newLeaves.leaveEndDate;
            window.history.replaceState(null, '');
        }
    }

    planLeavesFunction(leavesList){
        if(this.state.editLeaveId !== ""){
            leavesList["leaveId"] = this.state.editLeaveId;
            axios.request({
                method:'put',
                url:'http://localhost:8080/update-leaves',
                data: leavesList
            }).then(response => {
                console.log(response.data);
                this.setState({isPlanned:2});
            }).catch(error => {
                console.log(error.response);
                this.setState({leaveMessage:error.response.data});
            });
        }
        else{
            axios.request({
                method:'post',
                url:'http://localhost:8080/leave-details',
                data: leavesList
            }).then(response => {
                console.log(response.data);
                this.setState({isPlanned:1});
            }).catch(error => {
                console.log(error.response);
                this.setState({leaveMessage:error.response.data});
            });
        }
    }

    onSubmit(e){
        var mailFormat = /^([A-Za-z0-9_\-.])+@([A-Za-z0-9_\-.])+.([A-Za-z]{2,4})$/;
        const leaveDetails ={
            rollNumber : rollNumber,
            leaveType : this.state.leaveType.value,
            startDate : this.refs.startDate.value,
            endDate : this.refs.endDate.value,
            emailId : this.refs.email.value,
            studentToken : JSON.parse(localStorage.getItem("studentToken")).token
        };
        if (leaveDetails.leaveType !== '' && leaveDetails.startDate !== '' && leaveDetails.endDate !== ''){
            if(leaveDetails.emailId !== ''){
                var listOfEmails = leaveDetails.emailId.split(",");
                var flag=0;
                for(var i=0;i<listOfEmails.length;i++){
                    if(mailFormat.test(listOfEmails[i])){
                        flag=0;
                    }
                    else{
                        flag=1;
                        break;
                    }
                }
                if(flag === 0){
                    this.planLeavesFunction(leaveDetails);
                    this.resetForm();
                }
                else if(flag === 1){
                    this.setState({leaveMessage:"Invalid Email Format"});
                }
            }
            else {
                this.planLeavesFunction(leaveDetails);
                this.resetForm();
            }
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
        console.log(document.getElementById("dropDowns"));
        this.setState({leaveType:''});
        document.getElementById("startDate").defaultValue = "";
        document.getElementById("endDate").defaultValue = "";
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
        if(this.state.isPlanned > 0) {
            if(this.state.isPlanned === 1){
                toastMessage =
                {
                    title: 'Success',
                    description: 'Leave is planned successfully',
                    backgroundColor: '#5cb85c',
                    icon: checkIcon
                };
                toaster = <Toast toast={toastMessage} page="planning-dashboard"/>;
            }
            else if(this.state.isPlanned === 2){
                toastMessage =
                {
                    title: 'Success',
                    description: 'Leave is updated successfully',
                    backgroundColor: '#5cb85c',
                    icon: checkIcon
                };
                toaster = <Toast toast={toastMessage} page="planning-dashboard"/>;
            }
        }
        else{
            if (this.state.leaveMessage !== ""){
                toastMessage =
                {
                    title: 'Failure',
                    description: this.state.leaveMessage,
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
                                <Select className="boxes dropdown-texts" id="dropDowns" defaultValue={{label: "Kundi", value: "Kundi"}} options={leaves} onChange={this.getDropDownValue} value={leaveType} placeholder="Select a leave type"/>
                                <div className="texts size2 div1">Start Date&nbsp;<span className="red-color">*</span></div>
                                <div className="boxes"><input type="date" id="startDate" min={new Date().toISOString().split('T')[0]} onChange={this.setEndLimit} 
                                className="form-control" ref="startDate"/></div>
                                <div className="texts size2 div4">End Date&nbsp;<span className="red-color">*</span></div>
                                <div className="boxes"><input type="date" id="endDate" min={this.state.endDateLimit} className="form-control" ref="endDate"/></div>
                                <div className="texts size2 div4">Send leave notifications to</div>
                                <div className="boxes"><input type="text" className="form-control" ref="email"/></div>
                                <p className="texts div5">Use comma seperated values (not spaces)</p>
                                <button type="button"className="button1" onClick={this.resetForm}>Reset</button> 
                                <button type="submit" className="button2">Add to Plan</button>
                            </form>
                            <p className="texts div6"><span className="red-color">*</span>Mandatory</p>
                        </div>

                        <div className="broad-rectangle">
                            <div className="texts size1 broad-div">Planning Dashboard</div>
                            <div className="lines-new"></div>
                            <div className="broad-center-text">Planning Calendar</div>
                            <div className="lines-new lines-coordinate"></div>
                            <div className="new-div-border">
                                <CustomDatePicker />
                            </div>
                            <div className="vertical-bar"></div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default PlanningDashboard;
import { Component } from 'react';
import './planning-dashboard.css';
import  Dropdown  from 'react-dropdown';
import 'react-dropdown/style.css';
import axios from 'axios';

//import Select from 'react-select';
//import DatePicker from "react-datepicker";
//import { FontAwesome } from 'react-fontawesome';

var rollNumber;
var name;
class PlanningDashboard extends Component{

    constructor(props){
        super(props);
        this.state ={
            leaveType: ''
        };
        this.onSubmit = this.onSubmit.bind(this);
        this.planLeavesFunction = this.planLeavesFunction.bind(this);
        this.getDropDownValue = this.getDropDownValue.bind(this);
        this.resetForm = this.resetForm.bind(this);
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
            leaveType : this.state.leaveType,
            startDate : this.refs.startDate.value,
            endDate : this.refs.endDate.value,
            emailId : this.refs.email.value
        };
        console.log(leaveDetails);
        this.planLeavesFunction(leaveDetails);
        e.preventDefault();
    }

    getDropDownValue(e){
        console.log(e.value);
        this.setState({leaveType : e.value});
    }

    resetForm(){
        document.getElementById("planning-tab").reset();
        //document.getElementById("leavetype-dropdown").value="";
    }

    render(){
        console.log(this.props)
        const leaves = [
            {value: 'Casual Leave', label:'Casual Leave'},
            {value: 'Sick Leave', label:'Sick Leave'},
        ];
        if (typeof this.props.location.state === 'undefined')
        {
            rollNumber = "17PW";
            name = "John Doe"
        }
        else
        {
            rollNumber = this.props.location.state.detail.rollNumber;
            name = this.props.location.state.detail.name;
        }
        return(
            <div>
                <div className="rectangle"> 
                    <div className="texts size1 div1">Plan leaves</div>
                    <div className="lines-new"></div>
                    <div className="texts size2 div2">Roll No &nbsp;&nbsp;{rollNumber}</div>
                    <div className="texts size2 div3">Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{name}</div>
                    <form id="planning-tab" onSubmit={this.onSubmit}> 
                        <div className="texts size2 div1">Leave type &nbsp;<span className="red-color">*</span></div>
                        <Dropdown id="leavetype-dropdown" className="boxes" placeholderClassName="dropdown-texts" menuClassName="dropdown-texts"
                        options={leaves} onChange={this.getDropDownValue} placeholder="Select a leave type"
                        />
                        <div className="texts size2 div1">Start Date&nbsp;<span className="red-color">*</span></div>
                        <div className="boxes"><input type="date" className="form-control" ref="startDate"/></div>
                        <div className="texts size2 div4">End Date&nbsp;<span className="red-color">*</span></div>
                        <div className="boxes"><input type="date" className="form-control" ref="endDate"/></div>
                        <div className="texts size2 div4">Send leave notifications to &nbsp;<span className="red-color">*</span></div>
                        <div className="boxes"><input type="text" className="form-control" ref="email"/></div>
                        <p className="texts div5">Use comma seperated values (not spaces)</p>
                        <button className="button1" onClick={this.resetForm}>Reset</button> 
                        <button type="submit" className="button2">Add to Plan</button>
                    </form>
                    <p className="texts div6"><span className="red-color">*</span>Mandatory</p>
                </div>
            </div>
        );
    }

}

export default PlanningDashboard;
import { Component } from 'react';
import './planning-dashboard.css';
import  Dropdown  from 'react-dropdown';
import 'react-dropdown/style.css';
//import NavBar from './components/nav-bar';
//import DatePicker from "react-datepicker";
//import { FontAwesome } from 'react-fontawesome';

class PlanningDashboard extends Component{


    render(){
        console.log(this.props)
        var rollNumber;
        var name;
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
                    <div className="texts size2 div1">Leave type &nbsp;<span className="red-color">*</span></div>
                    <Dropdown style={{color: '#F7F3F3'}} className="boxes" placeholderClassName="dropdown-texts" menuClassName="dropdown-texts"
                    options={leaves} onChange={this._onSelect} placeholder="Select a leave type" />
                    <div className="texts size2 div1">Start Date&nbsp;<span className="red-color">*</span></div>
                    <div className="boxes"></div>
                    <div className="texts size2 div4">End Date&nbsp;<span className="red-color">*</span></div>
                    <div className="boxes"></div>
                    <div className="texts size2 div4">Send leave notifications to &nbsp;<span className="red-color">*</span></div>
                    <div className="boxes"></div>
                    <p className="texts div5">Use comma seperated values (not spaces)</p>
                    <button className="button1">Reset</button> 
                    <button className="button2">Add to Plan</button>
                    <p className="texts div6"><span className="red-color">*</span>Mandatory</p>
                </div>
            </div>
        );
    }

}

export default PlanningDashboard;
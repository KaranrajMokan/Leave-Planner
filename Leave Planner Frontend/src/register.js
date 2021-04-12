import { Component } from 'react';
import logo from './images/LP-logo.png';
import './register.css';
import axios from 'axios';
import Toast from './components/toast';
import errorIcon from './images/error_icon.png';

var toaster="";
class Register extends Component{

    constructor(){
        super();
        this.state={
            message: ""
        }
        this.submitData = this.submitData.bind(this);
        this.registerUser = this.registerUser.bind(this);
    }

    registerUser(data){
        axios.request({
            method:'post',
            url:'http://localhost:8080/register-users',
            data: data
        }).then(response => {
            console.log(response.data);
            this.props.history.push({
                pathname:"/",
                state:{registerData : response.data}
              });
        }).catch(error => {
            console.log(error.response);
        });
    }

    submitData(e){
        const userDetails = {
            rollNumber: this.refs.rollno.value.toUpperCase(),
            name : this.refs.name.value,
            email : this.refs.email.value,
            classId : this.refs.classId.value.toUpperCase(),
            password: this.refs.password1.value
        }
        const repassword = this.refs.password2.value
        var mailFormat = /^([A-Za-z0-9_\-.])+@([A-Za-z0-9_\-.])+.([A-Za-z]{2,4})$/;
        if(userDetails.rollno !=="" && userDetails.name !=="" && userDetails.email !=="" && userDetails.classId !=="" && userDetails.password!=="" && repassword!==""){
            if(mailFormat.test(userDetails.email)){
                if(userDetails.password === repassword){
                    this.registerUser(userDetails);
                }
                else{
                    this.setState({message:"Passwords mismatch"});
                }
            }
            else{
                this.setState({message:"Invalid mail format"});
            }
        }
        else{
            this.setState({message:"Missing Inputs"});
        }
        e.preventDefault();
    }

    render(){

        if(this.state.message !== ""){
            var toastMessage =
            {
                title: 'Failure',
                description: this.state.message,
                backgroundColor: '#d9534f',
                icon: errorIcon
            };
            toaster = <Toast toast={toastMessage} page="register"/>;
        }

        return(
            <div>
                {toaster}
                <img className="image-div" src={logo} alt=""></img>
                <div className="line-div">
                    <div className="inner-div"></div>
                    <div className="regis-rect">
                        <p className="title-reg"> Welcome to Leave Planner </p>
                        <div className="lines-reg"></div>
                        <form onSubmit={this.submitData}>  
                            <div className="pad-reg">
                                <p className="font-sty pos1">Roll number</p>
                                <div className="boxy center">
                                    <input type="text" className="form-control" placeholder="Enter rollno" ref="rollno"/>
                                </div>
                            </div>

                            <div className="pad-reg">
                                <p className="font-sty pos2">Name</p>
                                <div className="boxy center">
                                    <input type="text" className="form-control" placeholder="Enter name" ref="name"/>
                                </div>
                            </div>

                            <div className="pad-reg">
                                <p className="font-sty pos3">Email Id</p>
                                <div className="boxy center">
                                    <input type="email" className="form-control" placeholder="Enter email ID" ref="email"/>
                                </div>
                            </div>

                            <div className="pad-reg">
                                <p className="font-sty pos3">Class Id <span className="minimal">(eg:17PW/17PT/17PD)</span></p>
                                <div className="boxy center">
                                    <input type="text" className="form-control" placeholder="Enter class ID" ref="classId"/>
                                </div>
                            </div>

                            <div className="pad-reg">
                                <p className="font-sty pos3">Password</p>
                                <div className="boxy center">
                                    <input type="password" className="form-control" placeholder="Enter password" ref="password1"/>
                                </div>
                            </div>

                            <div className="pad-reg">
                                <p className="font-sty pos3">Retype Password</p>
                                <div className="boxy center">
                                    <input type="password" className="form-control" placeholder="Confirm password" ref="password2"/>
                                </div>
                            </div>

                            <div className="pad-button">
                                <button type="submit" className="submit center"><p className="but-text">Register</p></button>
                            </div>   
                        </form>
                    </div>
                </div>
            </div>
        );
    }
}

export default Register;
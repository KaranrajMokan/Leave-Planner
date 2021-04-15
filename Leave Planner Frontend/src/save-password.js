import { Component } from 'react';
import logo from './images/LP-logo.png'
import axios from 'axios';
import './forgot-password.css';
import Toast from './components/toast';
import checkIcon from './images/check_icon.png';
import errorIcon from './images/error_icon.png';

var toaster;
var toastMessage;
class SavePassword extends Component{

    constructor(){
        super();
        this.state = {
            errorMessage : ""
        }
    }

    onSubmitPassword(e){
        const pass1 = this.refs.password1.value;
        const pass2 = this.refs.password2.value;
        if(pass1 === pass2){
            const rollNumber = sessionStorage.getItem('rollNumber');
            const LoginDetails = {
                rollno : rollNumber,
                password : pass1
            }
            axios.request({
                method:'post',
                url:'http://localhost:8080/save-password',
                data: LoginDetails
            }).then(response=> {
                console.log(response.data);
                sessionStorage.removeItem('rollNumber');
                this.props.history.push({
                    pathname:"/",
                    state:{ savePasswordMessage : response.data }
                  });
            }).catch(error => {
                console.log(error.response.data);
                this.setState({errorMessage: error.response.data});
            });
        }
        else{
            this.setState({errorMessage:"Passwords mismatch"});
        }
        e.preventDefault();
    }

    render(){

        if(this.state.errorMessage !== ""){
            toastMessage =
            {
                title: 'Failure',
                description: this.state.errorMessage,
                backgroundColor: '#d9534f',
                icon: errorIcon
            };
            toaster = <Toast toast={toastMessage} page="save-password"/>;
        }

        if(typeof this.props.location.state !== 'undefined'){
            if (this.props.location.state.details !== ""){
                sessionStorage.setItem("rollNumber",this.props.location.state.details.rollNumber);
                toastMessage =
                {
                    title: 'Success',
                    description: this.props.location.state.details.message,
                    backgroundColor: '#5cb85c',
                    icon: checkIcon
                };
                toaster = <Toast toast={toastMessage} page="save-password"/>;
            }
            window.history.replaceState(null, '');
        }

        return(
            <div>
                {toaster}
                <img className="image-div" src={logo} alt=""></img>
                <div className="line-div">
                    <div className="inner-div">
                        <div className="login-component2">
                            <p className="title-text"> Password Reset </p>
                            <div className="lines"></div>
                            <form onSubmit={this.onSubmitPassword.bind(this)}> 
                                <div className="pad">
                                    <p className="fonts posey1">New Password</p> 
                                    <div className="box center">
                                        <input type="password" className="form-control" placeholder="Enter new password" ref="password1"/>
                                    </div>
                                </div>
                                <div className="pad">
                                    <p className="fonts">Retype Password</p> 
                                    <div className="box center">
                                        <input type="password" className="form-control" placeholder="Confirm password" ref="password2"/>
                                    </div>
                                </div>
                                <div className="pad-but">
                                    <button type="submit" className="submit center"><p className="but-text">Submit</p></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default SavePassword;
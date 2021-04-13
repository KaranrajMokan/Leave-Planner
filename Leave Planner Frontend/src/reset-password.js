import { Component } from 'react';
import axios from 'axios';
import Logo from './images/LP-logo.png';

import Toast from './components/toast';
import errorIcon from './images/error_icon.png';

var toaster="";
class ResetPassword extends Component{

    constructor(){
        super();
        this.state = {
            errorMessage : ""
        }
    }

    onSubmit(e){
        var oldPass = this.refs.oldPassword.value;
        var newPass = this.refs.newPassword.value;
        if(oldPass !== "" && newPass !== ""){
            const details = JSON.parse(localStorage.getItem("studentToken"));
            const resetDetails = {
                rollNumber : details.rollNumber,
                token : details.token,
                oldPassword : oldPass,
                newPassword : newPass
            }
            axios.request({
                method:'post',
                url:'http://localhost:8080/change-password',
                data: resetDetails
            }).then(response=> {
                console.log(response.data);
                this.props.history.push({
                    pathname:"/",
                    state:{resetData : response.data}
                  });
            }).catch(error => {
                console.log(error.response);
                this.setState({errorMessage:error.response.data});
            });
        }
        else{
            this.setState({errorMessage:"Missing inputs"});
        }
        e.preventDefault();
    }

    render(){

        if(this.state.errorMessage !== ""){
            var toastMessage =
            {
                title: 'Failure',
                description: this.state.errorMessage,
                backgroundColor: '#d9534f',
                icon: errorIcon
            };
            toaster = <Toast toast={toastMessage} page="reset-password"/>;
        }

        return(
            <div>
                {toaster}
                <img className="image-div" src={Logo} alt=""></img>
                <div className="line-div">
                    <div className="inner-div">
                        <div className="login-component">
                            <p className="title-text"> Reset Password </p>
                            <div className="lines"></div>
                            <p className="fonts position1">Current Password</p>
                            <p className="fonts position2">New Password</p>
                            <form onSubmit={this.onSubmit.bind(this)}>  
                                <div className="pad">
                                    <div className="box center">
                                        <input type="password" className="form-control" placeholder="Enter current password" ref="oldPassword"/>
                                    </div>
                                </div>

                                <div className="pad">
                                    <div className="box center">
                                        <input type="password" className="form-control" placeholder="Enter new password" ref="newPassword"/>
                                    </div>
                                </div>

                                <div className="pad-but">
                                    <button type="submit" className="submit center"><p className="but-text">Reset</p></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default ResetPassword;
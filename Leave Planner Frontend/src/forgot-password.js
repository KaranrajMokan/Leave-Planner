import { Component } from 'react';
import logo from './images/LP-logo.png'
import axios from 'axios';
import './forgot-password.css';
import Toast from './components/toast';
import errorIcon from './images/error_icon.png';

var toaster;
var toastMessage;
class ForgotPassword extends Component{

    constructor(){
        super();
        this.state = {
            errorMessage : ""
        }
    }

    onSubmitRollNumber(e){
        const rollNumber = this.refs.rollno.value.toUpperCase();
        console.log(rollNumber);
        if(rollNumber !== ""){
            axios.request({
                method:'post',
                url:'http://localhost:8080/forgot-password?rollNumber='+rollNumber,
                data: rollNumber
            }).then(response=> {
                console.log(response.data);
                this.props.history.push({
                    pathname:"/validate-token",
                    state:{ message : response.data }
                  });
            }).catch(error => {
                console.log(error.response.data);
                this.setState({errorMessage: error.response.data});
            });
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
            toaster = <Toast toast={toastMessage} page="forgot-password"/>;
        }

        return(
            <div>
                {toaster}
                <img className="image-div" src={logo} alt=""></img>
                <div className="line-div">
                    <div className="inner-div">
                        <div className="login-component1">
                            <p className="title-text"> Forgot Password? </p>
                            <div className="lines"></div>
                            <form onSubmit={this.onSubmitRollNumber.bind(this)}> 
                                <div className="pad">
                                    <p className="fonts posey1">Roll Number</p> 
                                    <div className="box center">
                                        <input type="text" className="form-control" placeholder="Enter rollno" ref="rollno"/>
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

export default ForgotPassword;
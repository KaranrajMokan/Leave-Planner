import { Component } from 'react';
import logo from './images/LP-logo.png'
import axios from 'axios';
import './forgot-password.css';
import Toast from './components/toast';
import checkIcon from './images/check_icon.png';
import errorIcon from './images/error_icon.png';

var toaster;
var toastMessage;
class ValidateToken extends Component{

    constructor(){
        super();
        this.state = {
            errorMessage : ""
        }
    }

    onSubmitToken(e){
        const token = this.refs.token.value;
        if(token !== ""){
            axios.request({
                method:'get',
                url:'http://localhost:8080/validate-token?token='+token,
                data: token
            }).then(response=> {
                console.log(response.data);
                var message = response.data;
                message = message.split('for');
                this.props.history.push({
                    pathname:"/save-password",
                    state:{ 
                        details: 
                        {   
                            message : message[0],
                            rollNumber : message[1]
                        } 
                    }
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
            toaster = <Toast toast={toastMessage} page="validate-token"/>;
        }

        if(typeof this.props.location.state !== 'undefined'){
            if (this.props.location.state.message !== ""){
                toastMessage =
                {
                    title: 'Success',
                    description: this.props.location.state.message,
                    backgroundColor: '#5cb85c',
                    icon: checkIcon
                };
                toaster = <Toast toast={toastMessage} page="validate-token"/>;
            }
            window.history.replaceState(null, '');
        }

        return(
            <div>
                {toaster}
                <img className="image-div" src={logo} alt=""></img>
                <div className="line-div">
                    <div className="inner-div">
                        <div className="login-component1">
                            <p className="title-text"> Token Validation </p>
                            <div className="lines"></div>
                            <form onSubmit={this.onSubmitToken.bind(this)}> 
                                <div className="pad">
                                    <p className="fonts posey1">Token</p> 
                                    <div className="box center">
                                        <input type="text" className="form-control" placeholder="Enter token" ref="token"/>
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

export default ValidateToken;
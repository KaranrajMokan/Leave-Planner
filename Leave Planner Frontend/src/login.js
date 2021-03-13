import { Component } from 'react';
import './login.css';
import axios from 'axios';

class Login extends Component{

    logIn(cred)
    {
        axios.request({
            method:'post',
            url:'http://localhost:8080/login-details',
            data: cred
        }).then(response=> {
            console.log(response.data);
            this.props.history.push({
                pathname:"/planning-dashboard",
                state:{ detail:response.data }
            });
        }).catch(error => {
            console.log(error.response)
        });
    }


    onSubmit(e)
    {
        const List = {
            rollno: this.refs.rollno.value.toUpperCase(),
            password: this.refs.password.value
        }
        console.log(List);
        this.logIn(List);
        e.preventDefault();
    }

    render(){
        return(
            <div>
                <div className="login-component">
                    <p className="title-text"> Welcome to Leave Planner </p>
                    <div className="lines"></div>
                    <p className="fonts position1">Roll number</p>
                    <p className="fonts position2">Password</p>
                    <form onSubmit={this.onSubmit.bind(this)}>  
                        <div className="pad">
                            <div className="box center">
                                <input type="text" className="form-control" placeholder="Enter rollno" ref="rollno"/>
                            </div>
                        </div>

                        <div className="pad">
                            <div className="box center">
                                <input type="password" className="form-control" placeholder="Enter password" ref="password"/>
                            </div>
                        </div>

                        <div className="pad-but">
                            <button type="submit" className="submit center"><p className="but-text">Login</p></button>
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}

export default Login;
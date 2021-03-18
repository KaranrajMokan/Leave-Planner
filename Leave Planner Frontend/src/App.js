import { Component } from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { BrowserRouter as Switch, Route} from "react-router-dom";
import logo from './images/LP-logo.png';
import Login from './login';
import PlanningDashboard from './planning-dasboard';
import NavBar from './components/nav-bar';
import { withRouter } from 'react-router';

var isLoggedIn;
var navbar;
var logout;
var name;
class App extends Component {

  componentWillMount(){
    if (this.getWithExpiry()){
      navbar = <NavBar/>;
      logout = <button className="logout-but end-texts" onClick={this.logoutFunction}>LOGOUT</button>;
      const halfName = "Hello, "+JSON.parse(localStorage.getItem("studentToken")).name;
      name = <div className="positions end-texts">{halfName}</div>;
    }
    else{
      navbar = '';
      logout = '';
      name = '';
    }
  };

  getWithExpiry(){
    const itemStr = localStorage.getItem("studentToken")
    if(!itemStr){
      isLoggedIn = false;
    }
    else{
      const item=JSON.parse(itemStr)
      const now = new Date();
      if (now.getTime() > item.expiry){
        isLoggedIn = false;
        localStorage.clear();
        this.props.history.push("/");
      }
      else{
        isLoggedIn = true;
      }
      console.log(now.getTime());
      console.log(item.expiry);
    }
    console.log(isLoggedIn);
    return isLoggedIn;
  };

  logoutFunction(){
    localStorage.clear();
    isLoggedIn = false;
    window.location.href = '/';
  };

  render() {
    return (
      <div>
        <img className="image-div" src={logo} alt=""></img>
        <div>{navbar}{name}{logout}</div>
        <div className="line-div">
          <div className="inner-div">
          <Switch>
            <Route path="/" exact render={props=><Login {...props}/>}/>
            <Route path="/planning-dashboard" exact render={props=><PlanningDashboard {...props}/>}/>
          </Switch>
          </div>
        </div>
      </div>);
  }

}

export default withRouter(App);

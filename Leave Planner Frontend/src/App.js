import { Component } from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { BrowserRouter as Switch, Route} from "react-router-dom";
import logo from './images/LP-logo.png';
import Login from './login';
import PlanningDashboard from './planning-dasboard';
import LeavesDashboard from './leaves-dashboard';
import NavBar from './components/nav-bar';
import { withRouter } from 'react-router';

var isLoggedIn;
var navbar;
var logout;
var name;
var lines;
class App extends Component {

  constructor() {
    super();
    this.state = {
       clicked: "Planning"
    }
    this.myCallBack = this.myCallBack.bind(this);
    this.getWithExpiry = this.getWithExpiry.bind(this);
    this.logoutFunction = this.logoutFunction.bind(this);
  };

  componentWillMount(){
    if (this.getWithExpiry()){
      navbar = <NavBar callBackFromParent={this.myCallBack}/>;
      logout = <button className="logout-but end-texts" onClick={this.logoutFunction}>LOGOUT</button>;
      const halfName = "Hello, "+JSON.parse(localStorage.getItem("studentToken")).name;
      name = <div className="positions end-texts">{halfName}</div>;
    }
    else{
      navbar = '';
      logout = '';
      name = '';
      lines = '';
    }
  };

  myCallBack = (dataFromChild) => {
    this.setState({ clicked: dataFromChild});
    console.log(dataFromChild);
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
    }
    return isLoggedIn;
  };

  logoutFunction(){
    localStorage.clear();
    isLoggedIn = false;
    window.location.href = '/';
  };

  render() {
    if(isLoggedIn){
      if(this.state.clicked === "Planning"){
        console.log("yes");
        lines = <div className="app-lines"></div>;
      }
      else if (this.state.clicked === "Leaves"){
        console.log("no");
        lines = <div className="app-lines change-speed"></div>;
      }
    }
    return (
      <div>
        <img className="image-div" src={logo} alt=""></img>
        {lines}
        <div>{navbar}{name}{logout}</div>
        <div className="line-div">
          <div className="inner-div">
          <Switch>
            <Route path="/" exact render={props=><Login {...props}/>}/>
            <Route path="/planning-dashboard" exact render={props=><PlanningDashboard {...props}/>}/>
            <Route path="/leaves-dashboard" exact render={props=><LeavesDashboard {...props}/>}/>
          </Switch>
          </div>
        </div>
      </div>);
  }

}

export default withRouter(App);

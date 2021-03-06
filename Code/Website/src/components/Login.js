import React, { Component } from "react";
import { Link, withRouter } from "react-router-dom";
import { Form, FormGroup, Input, Button, NavLink } from "reactstrap";
import axios from "axios";
import https from "https";

class Login extends Component {
  componentDidMount() {
    console.log(this.props);
  }
  state = {
    email: "",
    password: ""
  };

  handleSubmit = e => {
    e.preventDefault();
    console.log(this.state.email + " " + this.state.password);
    const agent = new https.Agent({
      rejectUnauthorized: false
    });
    axios
      .post(
        "https://localhost:8080/login",
        {
          email: this.state.email,
          password: this.state.password
        },
        { crossdomain: true, httpsAgent: agent, withCredentials: true }
      )
      .then(res => {
        const str = "SUCCESS!";
        console.log(res);
        this.props.handleLogIn(
          res.data.name,
          res.data.userType,
          res.data.sessionKey,
          res.data.userId,
          res.data.url
        );
        if (res.data.userType === "BookStoreAdmin") {
          this.props.history.push("/bookstore_admin");
        } else if (res.data.userType === "LibraryAdmin") {
          this.props.history.push("/library_admin");
        } else {
          this.props.history.push("/");
        }
      })
      .catch(error => {
        window.alert(`${error}
                       Your e-mail or password is incorrect
                       `);
      });
  };

  handleLogInkFormChange = e => {
    switch (e.target.id) {
      case "email":
        {
          this.setState({
            email: e.target.value
          });
        }
        break;
      case "password":
        {
          this.setState({
            password: e.target.value
          });
        }
        break;
    }
  };

  render() {
    return (
      <div className="container">
        <div className="row">
          <div className="col-sm-6 offset-sm-3 pt-5">
            <h2 className="text-center display-4">Log in</h2>
          </div>
        </div>
        <div className="row">
          <div className="offset-sm-3 col-sm-6 p-5">
            <p>Enter your credentials in order to log in:</p>
            <Form onSubmit={e => this.handleSubmit(e)}>
              <FormGroup>
                <p>
                  Email:
                  <Input
                    type="text"
                    value={this.state.email}
                    onChange={this.handleLogInkFormChange}
                    name="emailInput"
                    id="email"
                    placeholder="email"
                  />
                </p>
                <p />
                <p>
                  Password:
                  <Input
                    type="password"
                    value={this.state.password}
                    onChange={this.handleLogInkFormChange}
                    name="password"
                    id="password"
                    placeholder="password"
                  />
                </p>
                <p />
                <div className="text-center">
                  <Button color="primary" size="sm">
                    Log in
                  </Button>
                </div>
              </FormGroup>
            </Form>
          </div>
        </div>
      </div>
    );
  }
}

export default withRouter(Login);

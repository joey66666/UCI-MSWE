function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

function start() {
  class ClickTime extends React.Component {
    constructor(props) {
      super(props);

      _defineProperty(this, "text", this.props.text);

      _defineProperty(this, "number", this.props.number);

      console.log("Click time component created");
    }

    render() {
      return /*#__PURE__*/React.createElement("div", null, /*#__PURE__*/React.createElement("p", null, this.text, " ", this.number), /*#__PURE__*/React.createElement("button", {
        onClick: () => {
          this.number = this.number + 1;
          this.setState({});
        }
      }, " Click me"));
    }

  }

  _defineProperty(ClickTime, "propTypes", {
    number: PropTypes.number
  });

  ReactDOM.render( /*#__PURE__*/React.createElement("div", null, /*#__PURE__*/React.createElement(ClickTime, {
    text: "You have clicked: ",
    number: 0
  })), document.getElementById("click_time"));
}

function start() {
    class ClickTime extends React.Component {
        constructor(props) {
            super(props);
            console.log("Click time component created");
        }

        static propTypes = {number: PropTypes.number}
        text = this.props.text
        number = this.props.number
        render() {
            return (
                <div>
                    <p>{this.text} {this.number}</p>
                    <button onClick={() => {
                        this.number = this.number + 1;
                        this.setState({});
                    }}> Click me
                    </button>
                </div>
            );
        }
    }


    ReactDOM.render(
        <div>
            <ClickTime text={"You have clicked: "}
                       number={0}/>
        </div>, document.getElementById("click_time"));
}

$(function () {
    "use strict";

    // chart 2
    var ctx = document.getElementById("chart2").getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ["Máy chơi game", "Đĩa game", "Ghế gaming", "Phụ kiện"],
            datasets: [{
                    backgroundColor: [
                        "#ffffff",
                        "rgba(255, 255, 255, 0.70)",
                        "rgba(255, 255, 255, 0.50)",
                        "rgba(255, 255, 255, 0.20)"
                    ],
                    data: [5000, 2602, 1802, 1105],
                    borderWidth: [0, 0, 0, 0]
                }]
        },
        options: {
            maintainAspectRatio: false,
            legend: {
                position: "bottom",
                display: false,
                labels: {
                    fontColor: '#ddd',
                    boxWidth: 15
                }
            }
            ,
            tooltips: {
                displayColors: false
            }
        }
    });
});
   
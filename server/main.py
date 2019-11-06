from flask import Flask, redirect
from flask import request, jsonify
from flask_restful import Resource, Api, reqparse
from flask_cors import CORS
import sqlalchemy 
import time

#engine = sqlalchemy.create_engine( "postgres://localhost/backpack")
engine = sqlalchemy.create_engine( 
    sqlalchemy.engine.url.URL(
        drivername='postgres+pg8000',
        username='postgres',
        password='postgres',
        database='postgres',
        host='localhost',
        port='5432',
    )
)
#engine = sqlalchemy.create_engine( 
#    sqlalchemy.engine.url.URL(
#        drivername='postgres+pg8000',
#        username='postgres',
#        password='postgres',
#        database='postgres',
#        query={
#            'unix_sock': '/cloudsql/{}/.s.PGSQL.5432'.format("southern-zephyr-258223:us-central1:retail-tech")
#        }
#    )
#)
connection = engine.connect()

app = Flask(__name__)
api = Api(app)
CORS(app)

@app.route('/')
@app.route('/test')
def test():
    return "hello, world!"

@app.route('/test_insert')
def test_insert():
    dum_name = '\'dumbill\''
    dum_number = 911
    dum_time = time.time()
    sql_query = """insert into test_table
        (name, number, time)
        values ({}, {}, {});""".format(
            dum_name, dum_number, dum_time
        )
    print(sql_query)
    result = connection.execute(sql_query)
    return "completed insert"

@app.route('/test_get')
def test_get():
    sql_query = """select time, name, number from test_table order by time desc limit 1;"""
    result = connection.execute(sql_query)
    row = result.first()
    loc_obj  = {
                "time": float(row["time"]),
                "name": row["name"],
                "number": float(row["number"])
           }
    return jsonify(loc_obj)
    
#@app.route('/gps')
#def gps():
#    sql_query = """select time, lat, long from gps order by time desc limit 1;"""
#    result = connection.execute(sql_query)
#    row = result.first()
#    loc_obj  = {
#                "time": float(row["time"]),
#                "lat": float(row["lat"]),
#                "long": float(row["long"])
#           }
#    return jsonify(loc_obj)
#
#@app.route('/insert_gps')
#def insert_gps():
#    dum_lat = 50
#    dum_long = 50
#    dum_time = time.time()
#    sql_query = """insert into gps
#        (lat, long, time)
#        values ({}, {}, {});""".format(
#            dum_lat, dum_long, dum_time
#        )
#    result = connection.execute(sql_query)
#    return "completed insert"
#
#@app.route('/acceleration')
#def acceleration():
#    sql_query = """select time, acceleration from acceleration order by time
#    desc limit 1000;"""
#    result = connection.execute(sql_query)
#    row = result.first()
#    acc_obj  = {
#                "time": float(row["time"]),
#                "acceleration": float(row["acceleration"])
#           }
#    return jsonify(acc_obj)
#
#@app.route('/acceleration_hour')
#def acceleration_hour():
#    sql_query = """select time, acceleration from acceleration where time > {} - 600 order by time
#    desc;""".format(time.time())
#    result = connection.execute(sql_query)
#    acc_obj  = { 
#                "data": [
#                    {
#                        "time": float(row["time"]),
#                        "acceleration": float(row["acceleration"])
#                    }
#                    for row in result
#                ]
#           }
#    return jsonify(acc_obj)
#
#@app.route('/insert_acceleration')
#def insert_acceleration():
#    dum_acc = 50
#    dum_time = time.time()
#    sql_query = """insert into acceleration
#        (acceleration, time)
#        values ({}, {});""".format(
#            dum_acc, dum_time
#        )
#    result = connection.execute(sql_query)
#    return "completed insert"
#
#
#@app.route('/arduino_acceleration', methods=['POST'])
#def arduino_acceleration():
#    acc = request.form['acceleration']
#    upload_to_acceleration(int(acc)) 
#    return "uploaded arduino acceleration to db"
#
#@app.route('/arduino_gps', methods=['POST'])
#def arduino_gps():
#    pos = request.form['gps']
#    arr = pos.split(',')
#    lat = arr[1]
#    long = arr[0]
#    upload_to_gps(float(lat), float(long))
#    return "uploaded arduino gps to db"
#
#"""
#================================
#Helper functions to insert into the database
#================================
#"""
#def upload_to_gps(lat,long):
#    curr_time = time.time()
#    sql_query = """insert into gps
#        (lat, long, time)
#        values ({}, {}, {});""".format(
#            lat, long, curr_time
#        )
#    result = connection.execute(sql_query)
#    return "completed insert lat: {}, long: {}, time: {}".format(lat, long, curr_time)
#
#def upload_to_acceleration(acc):
#    curr_time = time.time()
#    sql_query = """insert into acceleration
#        (acceleration, time)
#        values ({}, {});""".format(
#            acc, curr_time
#        )
#    result = connection.execute(sql_query)
#    return "completed insert acceleration: {}, time: {}".format(acc, curr_time)

if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0") 

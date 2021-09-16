import mysql.connector
from mysql.connector import Error



def fetchOne(cursor,sql):
    cursor.execute(sql)
    result = cursor.fetchone()
    return result

def selectDescription(cursor, server,id):
    sql =  "SELECT * FROM description_report_"+server+" where id = '%s'" % (id)
    return fetchOne(cursor,sql)

def selectPlayer(cursor , server, id):
    sql = "SELECT * FROM player_"+server+" where id = '%s'" % (id)
    return fetchOne(cursor,sql)

def selectReportType(cursor, server, playerId, reportType):
    sql = "SELECT * FROM report_type_"+server+" where player_"+server+"_id = '%s' and report_type = '%s'" % (playerId,reportType)
    return fetchOne(cursor,sql)

def selectReportTypeRegion(cursor,server,reportType):
    sql = "SELECT * FROM  report_type_region WHERE region = '%s'  and report_type = '%s'" %(server,reportType)
    return fetchOne(cursor,sql)

def deleteDescription(cursor,server,id):
    sql =  "DELETE  FROM description_report_"+server+" where id = '%s'" % (id)
    cursor.execute(sql)

def deleteReportType(cursor, server,playerId, reportType):
    sql =  "DELETE  FROM report_type_"+server+" where player_"+server+"_id = '%s' and report_type = '%s'" % (playerId,reportType)
    cursor.execute(sql)

def deleteReportTypeRegion(cursor,server,reportType):
    sql = "DELETE FROM report_type_region WHERE region = '%s' and report_type = '%s'" %(server,reportType)
    cursor.execute(sql)

def deletePlayer(cursor , server,id):
    sql =  "DELETE  FROM player_"+server+" where id = '%s'" % (id)
    cursor.execute(sql)

def decrementPlayerCount(cursor ,server,id,count):
    sql =  "UPDATE player_"+server+" SET report_count = '%s' WHERE id = '%s'" %(count,id)
    cursor.execute(sql)

def decrementReportTypeCount(cursor, server,repCount,playerId,reportType):
    sql =  "UPDATE report_type_"+server+" SET count = "+str(repCount)+" WHERE player_"+server+"_id = '%s'  and report_type = '%s'" %(playerId,reportType)
    cursor.execute(sql)

def decrementReportTyRegion(cursor,server,repCount,reportType):
    sql = "UPDATE report_type_region SET count = "+str(repCount)+" WHERE region = '%s'  and report_type = '%s'" %(server,reportType)
    cursor.execute(sql)

def main():
    server = input("type the server: ")
    id = input("type id report: ")
    connection = mysql.connector.connect(host='localhost', database='reporekt',user='root', password='napolisoccer8814')
    cursor = connection.cursor()
    descriptionResult = selectDescription(cursor, server,id)
    print("---------------")
    if descriptionResult:
        deleteDescription(cursor,server,id)
        print("description deleted")
        playerId = descriptionResult[5]
        reportType = descriptionResult[3]
        reportTypeRegionResult = selectReportTypeRegion(cursor,server,reportType)
        if(reportTypeRegionResult[1]>1):
            reportTypeRegionCount = reportTypeRegionResult[1] - 1
            decrementReportTyRegion(cursor,server,reportTypeRegionCount,reportType)
            print("reporetTypeRegion decremented " + str(reportTypeRegionCount))
        else:
            deleteReportTypeRegion(cursor,server,reportType)
            print("reportTypeRegion deleted")
        playerResult = selectPlayer(cursor,server,playerId)
        if playerResult[3] > 1:
            playerCount = playerResult[3] - 1
            decrementPlayerCount(cursor,server,playerId,playerCount)
            print( "player decremented " + str(playerCount))
        else:
            deletePlayer(cursor,server,playerId)
            print("player deleted")
        reportTypeResult = selectReportType(cursor,server,playerId,reportType)
        if reportTypeResult[1] > 1:
            repCount = reportTypeResult[1] - 1
            decrementReportTypeCount(cursor,server,repCount,playerId,reportType)
            print("report type decremented " + str(repCount))
        else:
            deleteReportType(cursor,server,playerId,reportType)
            print("report type deleted")
        cursor.close()
        print("---------------")
    else: 
        cursor.close()
        print("report not found")
        print("---------------")



if __name__ == "__main__":
    main()



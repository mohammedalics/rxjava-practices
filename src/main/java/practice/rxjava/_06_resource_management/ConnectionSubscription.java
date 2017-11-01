package practice.rxjava._06_resource_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import rx.Subscription;

public class ConnectionSubscription implements Subscription {
    
    private final Connection connection;
    private final HashSet<Statement> statements = new HashSet<>();
    private final HashSet<ResultSet> resultSets = new HashSet<>();

    private boolean isSubscribed;

    public ConnectionSubscription(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void unsubscribe() {
        
        System.out.println( "Unsubscribe called!" );
        
        // Close all resultsets
        for( ResultSet rs : resultSets ) {
            System.out.println( "    Closing ResultSet -> " + rs.toString() );
            try { rs.close(); } catch( SQLException t ) {}
        }
        
        // Close all statements
        for( Statement s : statements ) {
            System.out.println( "    Closing Statement -> " + s.toString() );
            try { s.close(); } catch( SQLException t ) {}
        }
        
        if( connection != null ) {
            System.out.println( "    Closing Connection -> " + connection.toString() );
            try { connection.close(); } catch( SQLException e ) {}
        }

        isSubscribed = false;
    }

    @Override
    public boolean isUnsubscribed() {
        return isSubscribed;
    }

    public Connection getConnection() {
        return connection;
    }
    
    public void registerResourceForClose( Statement s ) {
        if( s == null ) {
            throw new IllegalArgumentException();
        }
        
        statements.add(s);
    }

    public void registerResourceForClose( ResultSet rs ) {
        if( rs == null ) {
            throw new IllegalArgumentException();
        }
        resultSets.add(rs);
    }
}

<?php

namespace App\Neo4j;

use Laudis\Neo4j\ClientBuilder;
use Laudis\Neo4j\Authentication\Authenticate;

class Driver
{
    protected $driver;

    public function __construct()
    {
        $this->driver = ClientBuilder::create()
            ->withDriver(
                'neo4j',
                'neo4j://' . env('NEO4J_HOST') . ':' . env('NEO4J_PORT') . '?database=' . env('NEO4J_DATABASE'),
                Authenticate::basic(env('NEO4J_USERNAME'), env('NEO4J_PASSWORD'))
            )
            ->build();
    }

    public function run($cypher, array $parameters = [])
    {
        return $this->driver->run($cypher, $parameters);
    }
}

#!/bin/sh

mvn -Pprod clean release:prepare && mvn -Pprod clean release:perform
